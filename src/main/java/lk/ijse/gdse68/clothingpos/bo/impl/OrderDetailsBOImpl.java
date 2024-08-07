//package lk.ijse.gdse68.clothingpos.bo.impl;
//
//import lk.ijse.gdse68.clothingpos.bo.OrderDetailsBO;
//import lk.ijse.gdse68.clothingpos.dao.impl.OrderDetailsDAOImpl;
//import lk.ijse.gdse68.clothingpos.dto.OrderDetailsDTO;
//
//import java.sql.Connection;
//import java.util.List;
//
//public class OrderDetailsBOImpl implements OrderDetailsBO {
//    OrderDetailsDAOImpl orderDetailsDAO = new OrderDetailsDAOImpl();
//
//    @Override
//    public String saveOrderDet(OrderDetailsDTO orderDetailsDTO, Connection connection) throws Exception {
//        return orderDetailsDAO.saveOrderDet(orderDetailsDTO, connection);
//    }
//
//    @Override
//    public boolean updateOrderDet(String id, OrderDetailsDTO orderDetailsDTO, Connection connection) throws Exception {
//        return orderDetailsDAO.updateOrderDet(id, orderDetailsDTO, connection);
//    }
//
//    @Override
//    public boolean deleteOrderDet(String id, Connection connection) throws Exception {
//        return orderDetailsDAO.deleteOrderDet(id, connection);
//    }
//
//    @Override
//    public List<OrderDetailsDTO> getAllOrderDet(Connection connection) throws Exception {
//        return orderDetailsDAO.getAllOrderDet(connection);
//    }
//}
