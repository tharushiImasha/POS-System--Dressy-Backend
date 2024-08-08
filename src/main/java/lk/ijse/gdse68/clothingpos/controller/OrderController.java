package lk.ijse.gdse68.clothingpos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.clothingpos.bo.BOFactory;
import lk.ijse.gdse68.clothingpos.bo.custom.PlaceOrderBO;
import lk.ijse.gdse68.clothingpos.dto.OrdersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/order", loadOnStartup = 2)
public class OrderController extends HttpServlet {

    PlaceOrderBO orderBO = (PlaceOrderBO) BOFactory.getBoFactory().getSuperBO(BOFactory.BOType.ORDER);

    Connection connection;
    static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/customerReg");
            this.connection = pool.getConnection();

        } catch (NamingException | SQLException e) {
            logger.error("Database connection error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid content type");
            logger.warn("Invalid content type: {}", req.getContentType());
            return;
        }

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            OrdersDTO ordersDTO = jsonb.fromJson(req.getReader(), OrdersDTO.class);

//            PlaceOrderBO placeOrderBO = new PlaceOrderBOImpl();

            boolean isSaved = orderBO.saveOrder(ordersDTO, connection);

            if (isSaved) {
                logger.info("Order saved successfully");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }

            resp.setContentType("application/json");
            jsonb.toJson(ordersDTO, writer);

        } catch (Exception e) {
            logger.error("Error saving order", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving order");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();

//            PlaceOrderBOImpl orderBO = new PlaceOrderBOImpl();
            List<OrdersDTO> ordersDTOS = orderBO.getAllOrder(connection);

            resp.setContentType("application/json");
            jsonb.toJson(ordersDTOS, writer);
            logger.info("Fetched all orders successfully");

        } catch (Exception e) {
            logger.error("Error fetching orders", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching orders");
        }
    }

}
