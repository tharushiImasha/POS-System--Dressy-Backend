package lk.ijse.gdse68.clothingpos.dao.impl;

import lk.ijse.gdse68.clothingpos.dao.OrdersDAO;
import lk.ijse.gdse68.clothingpos.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    public static String SAVE_ORDER = "INSERT INTO orders (order_id, cus_id, total, date) VALUES (?,?,?,?)";
    public static String GET_ORDER = "SELECT * FROM orders";

    @Override
    public String saveOrder(OrdersDTO ordersDTO, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(SAVE_ORDER);

            ps.setString(1, ordersDTO.getOrder_id());
            ps.setString(2, ordersDTO.getCus_id());
            ps.setString(3, String.valueOf(ordersDTO.getTotal()));
            ps.setDate(4, Date.valueOf(ordersDTO.getDate()));

            if (ps.executeUpdate() != 0) {
                return "Order save successfully";
            }else {
                return "Save Order failed";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<OrdersDTO> getAllOrder(Connection connection) throws Exception {

        List<OrdersDTO> ordersDTOS = new ArrayList<>();

        try {

            var ps = connection.prepareStatement(GET_ORDER);
            var rst = ps.executeQuery();

            while (rst.next()){
                OrdersDTO ordersDTO = new OrdersDTO();

                ordersDTO.setOrder_id(rst.getString("order_id"));
                ordersDTO.setCus_id(rst.getString("cus_id"));
                ordersDTO.setTotal(rst.getDouble("total"));
                ordersDTO.setDate(rst.getDate("date").toLocalDate());

                ordersDTOS.add(ordersDTO);
            }

            return ordersDTOS;

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
