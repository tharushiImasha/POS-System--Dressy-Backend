package lk.ijse.gdse68.clothingpos.bo.impl;

import lk.ijse.gdse68.clothingpos.bo.PlaceOrderBO;
import lk.ijse.gdse68.clothingpos.dao.impl.OrderDetailsDAOImpl;
import lk.ijse.gdse68.clothingpos.dao.impl.OrdersDAOImpl;
import lk.ijse.gdse68.clothingpos.dto.OrderDetailsDTO;
import lk.ijse.gdse68.clothingpos.dto.OrdersDTO;

import java.sql.Connection;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDetailsDAOImpl orderDetailsDAO = new OrderDetailsDAOImpl();
    OrdersDAOImpl ordersDAO = new OrdersDAOImpl();

//    @Override
//    public String saveOrder(OrdersDTO ordersDTO, Connection connection) throws Exception {
//        List<OrderDetailsDTO> orderDetails = ordersDTO.getOrder_details();
//        for (OrderDetailsDTO orderDetail : orderDetails) {
//            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
//            orderDetailsDTO.setOrder_id(orderDetail.getOrder_id());
//            orderDetailsDTO.setQuantity(orderDetail.getQuantity());
//            orderDetailsDTO.setCostume_id(orderDetail.getCostume_id());
//            orderDetailsDTO.setOrder_detail_id(orderDetail.getOrder_detail_id());
//
//            String s = orderDetailsDAO.saveOrderDet(orderDetail, connection);
//            return s;
//        }
//        String s = ordersDAO.saveOrder(ordersDTO, connection);
//        return s;
//    }

    @Override
    public String saveOrder(OrdersDTO ordersDTO, Connection connection) throws Exception {
        try {
            // Save the order first
            String orderResult = ordersDAO.saveOrder(ordersDTO, connection);

            // If order is saved successfully, save the order details
            if (orderResult != null) {
                List<OrderDetailsDTO> orderDetails = ordersDTO.getOrder_details();
                for (OrderDetailsDTO orderDetail : orderDetails) {
                    orderDetailsDAO.saveOrderDet(orderDetail, connection);
                }
            }

            return orderResult;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<OrdersDTO> getAllOrder(Connection connection) throws Exception {
        return ordersDAO.getAllOrder(connection);
    }
}
