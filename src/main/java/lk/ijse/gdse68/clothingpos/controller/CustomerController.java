package lk.ijse.gdse68.clothingpos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.clothingpos.bo.impl.CustomerBOImpl;
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

@WebServlet(urlPatterns = "/customer", loadOnStartup = 2)
public class CustomerController extends HttpServlet {

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
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            CustomerBOImpl customerBO = new CustomerBOImpl();

            writer.write(customerBO.saveCustomer(customerDTO, connection));
            logger.info("Customer saved successfully");
            resp.setStatus(HttpServletResponse.SC_CREATED);

            resp.setContentType("application/json");
            jsonb.toJson(customerDTO, writer);

        }catch (Exception e) {
            logger.error("Connection failed");
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {

            var cusId = req.getParameter("cus_id");
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            CustomerBOImpl customerBO = new CustomerBOImpl();

            if (customerBO.updateCustomer(cusId, customerDTO, connection)) {
                writer.write("Update customer successfully");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Update customer failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var cusId = req.getParameter("cus_id");
            CustomerBOImpl customerBO = new CustomerBOImpl();

            if (customerBO.deleteCustomer(cusId, connection)) {
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

//            var cusId = req.getParameter("cus_id");
            CustomerBOImpl customerBO = new CustomerBOImpl();
            List<CustomerDTO> customers = customerBO.getAllCustomers(connection);

            resp.setContentType("application/json");
            jsonb.toJson(customers, writer);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
