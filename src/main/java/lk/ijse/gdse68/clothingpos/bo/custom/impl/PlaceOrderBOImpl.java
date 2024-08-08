package lk.ijse.gdse68.clothingpos.bo.custom.impl;

import lk.ijse.gdse68.clothingpos.bo.custom.PlaceOrderBO;
import lk.ijse.gdse68.clothingpos.dao.DAOFactory;
import lk.ijse.gdse68.clothingpos.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse68.clothingpos.dao.custom.OrdersDAO;
import lk.ijse.gdse68.clothingpos.dto.OrderDetailsDTO;
import lk.ijse.gdse68.clothingpos.dto.OrdersDTO;
import lk.ijse.gdse68.clothingpos.entity.OrderDetails;
import lk.ijse.gdse68.clothingpos.entity.Orders;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getDaoFactory().getSuperDAO(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getSuperDAO(DAOFactory.DAOType.ORDERDETAIL);

    @Override
    public boolean saveOrder(OrdersDTO ordersDTO, Connection connection) throws Exception {
        try {
            // Save the order first
            boolean isSaved = ordersDAO.save(connection, new Orders(ordersDTO.getOrder_id(), ordersDTO.getCus_id(), ordersDTO.getTotal(), ordersDTO.getDate(), ordersDTO.getOrder_details()));

            // If order is saved successfully, save the order details
            if (isSaved) {
                List<OrderDetailsDTO> orderDetails = ordersDTO.getOrder_details();
                for (OrderDetailsDTO orderDetail : orderDetails) {
                    orderDetailsDAO.save(connection, new OrderDetails(orderDetail.getOrder_detail_id(), orderDetail.getOrder_id(), orderDetail.getCostume_id(), orderDetail.getQuantity()));

                }
            }

            return isSaved;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<OrdersDTO> getAllOrder(Connection connection) throws Exception {
        List<Orders> orders = ordersDAO.getAll(connection);
        List<OrdersDTO> ordersDTOS = new ArrayList<>();

        for (Orders order: orders) {
            ordersDTOS.add(new OrdersDTO(order.getOrder_id(), order.getCus_id(), order.getTotal(), order.getDate()));
        }
        return ordersDTOS;
    }

}
