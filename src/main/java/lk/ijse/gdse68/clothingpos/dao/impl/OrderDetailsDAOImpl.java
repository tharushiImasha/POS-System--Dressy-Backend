package lk.ijse.gdse68.clothingpos.dao.impl;

import lk.ijse.gdse68.clothingpos.dao.OrderDetailsDAO;
import lk.ijse.gdse68.clothingpos.dto.OrderDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    public static String SAVE_ORDER_DET = "INSERT INTO order_details (order_detail_id, order_id, costume_id, quantity) VALUES (?,?,?,?)";
    public static String GET_ORDER_DET = "SELECT * FROM order_details";

    @Override
    public String saveOrderDet(OrderDetailsDTO orderDetailsDTO, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(SAVE_ORDER_DET);

            ps.setString(1, orderDetailsDTO.getOrder_detail_id());
            ps.setString(2, orderDetailsDTO.getOrder_id());
            ps.setString(3, orderDetailsDTO.getCostume_id());
            ps.setString(4, String.valueOf(orderDetailsDTO.getQuantity()));

            if (ps.executeUpdate() != 0) {
                return "OrderDet save successfully";
            }else {
                return "Save OrderDet failed";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<OrderDetailsDTO> getAllOrderDet(Connection connection) throws Exception {

        List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();

        try {

            var ps = connection.prepareStatement(GET_ORDER_DET);
            var rst = ps.executeQuery();

            while (rst.next()){
                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

                orderDetailsDTO.setOrder_detail_id(rst.getString("order_detail_id"));
                orderDetailsDTO.setOrder_id(rst.getString("order_id"));
                orderDetailsDTO.setCostume_id(rst.getString("costume_id"));
                orderDetailsDTO.setQuantity(rst.getInt("quantity"));

                orderDetailsDTOS.add(orderDetailsDTO);
            }

            return orderDetailsDTOS;

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
