package lk.ijse.gdse68.clothingpos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.clothingpos.bo.BOFactory;
import lk.ijse.gdse68.clothingpos.bo.custom.CustomerBO;
import lk.ijse.gdse68.clothingpos.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/customer", loadOnStartup = 2)
public class CustomerController extends HttpServlet {

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getSuperBO(BOFactory.BOType.CUSTOMER);

    Connection connection;
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/customerReg");
            this.connection = pool.getConnection();

        } catch (NamingException | SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid content type");
            System.out.println("Bad request due to invalid content type");
            return; // Stop further processing
        }

        resp.setContentType("application/json");

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            boolean isSaved = customerBO.saveCustomer(connection, customerDTO);

            if (isSaved) {
                writer.write("Customer saved successfully");
                logger.info("Customer saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                System.out.println("Customer not saved");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to save customer");
            }

            jsonb.toJson(customerDTO, writer);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error("Failed to save customer: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            if (customerBO.updateCustomer(connection, customerDTO.getCus_id(), customerDTO)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content for successful update with no response body
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request for failure
                writer.write(jsonb.toJson(Map.of("message", "Update customer failed")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var cusId = req.getParameter("cus_id");

            if (customerBO.deleteCustomer(connection, cusId)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Delete customer failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer  = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();

            List<CustomerDTO> customers = customerBO.getAllCustomers(connection);

            resp.setContentType("application/json");
            jsonb.toJson(customers, writer);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
