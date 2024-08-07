//package lk.ijse.gdse68.clothingpos.controller;
//
//import jakarta.json.bind.Jsonb;
//import jakarta.json.bind.JsonbBuilder;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lk.ijse.gdse68.clothingpos.bo.impl.OrderDetailsBOImpl;
//import lk.ijse.gdse68.clothingpos.dto.OrderDetailsDTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//@WebServlet(urlPatterns = "/orderDet", loadOnStartup = 2)
//public class OrderDetailsController extends HttpServlet {
//
//    Connection connection;
//    static Logger logger = LoggerFactory.getLogger(OrderDetailsController.class);
//
//    @Override
//    public void init() throws ServletException {
//        try {
//            var ctx = new InitialContext();
//            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/customerReg");
//            this.connection = pool.getConnection();
//
//        } catch (NamingException | SQLException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//        }
//
//        try (var writer = resp.getWriter()) {
//            Jsonb jsonb = JsonbBuilder.create();
//            OrderDetailsDTO orderDetailsDTO = jsonb.fromJson(req.getReader(), OrderDetailsDTO.class);
//
//            OrderDetailsBOImpl orderDetailsBO = new OrderDetailsBOImpl();
//
//            writer.write(orderDetailsBO.saveOrderDet(orderDetailsDTO, connection));
//            logger.info("OrderDet saved successfully");
//            resp.setStatus(HttpServletResponse.SC_CREATED);
//
//            resp.setContentType("application/json");
//            jsonb.toJson(orderDetailsDTO, writer);
//
//        }catch (Exception e) {
//            logger.error("Connection failed");
//            System.out.println(e);
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try (var writer = resp.getWriter()) {
//            Jsonb jsonb = JsonbBuilder.create();
//            OrderDetailsDTO orderDetailsDTO = jsonb.fromJson(req.getReader(), OrderDetailsDTO.class);
//
//            OrderDetailsBOImpl orderDetailsBO = new OrderDetailsBOImpl();
//
//            if (orderDetailsBO.updateOrderDet(orderDetailsDTO.getOrder_detail_id(), orderDetailsDTO, connection)) {
//                resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content for successful update with no response body
//            } else {
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request for failure
//                writer.write(jsonb.toJson(Map.of("message", "Update item failed")));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try(var writer = resp.getWriter()) {
//            var orderDetailId = req.getParameter("order_detail_id");
//            OrderDetailsBOImpl orderDetailsBO = new OrderDetailsBOImpl();
//
//            if (orderDetailsBO.deleteOrderDet(orderDetailId, connection)) {
//                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//            }else {
//                writer.write("Delete orderDet failed");
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try (var writer  = resp.getWriter()){
//            Jsonb jsonb = JsonbBuilder.create();
//
//            OrderDetailsBOImpl orderDetailsBO = new OrderDetailsBOImpl();
//            List<OrderDetailsDTO> orderDetailsDTOS = orderDetailsBO.getAllOrderDet(connection);
//
//            resp.setContentType("application/json");
//            jsonb.toJson(orderDetailsDTOS, writer);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
