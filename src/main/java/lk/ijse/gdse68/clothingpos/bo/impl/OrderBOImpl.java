package lk.ijse.gdse68.clothingpos.bo.impl;

import lk.ijse.gdse68.clothingpos.bo.OrderBO;
import lk.ijse.gdse68.clothingpos.dao.impl.OrdersDAOImpl;
import lk.ijse.gdse68.clothingpos.dto.OrdersDTO;

import java.sql.Connection;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    OrdersDAOImpl ordersDAO = new OrdersDAOImpl();

    @Override
    public String saveOrder(OrdersDTO ordersDTO, Connection connection) throws Exception {
        return ordersDAO.saveOrder(ordersDTO, connection);
    }

    @Override
    public List<OrdersDTO> getAllOrder(Connection connection) throws Exception {
        return ordersDAO.getAllOrder(connection);
    }

}
