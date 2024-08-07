package lk.ijse.gdse68.clothingpos.bo;

import lk.ijse.gdse68.clothingpos.dto.OrdersDTO;

import java.sql.Connection;
import java.util.List;

public interface OrderBO {
    String saveOrder(OrdersDTO ordersDTO, Connection connection)throws Exception;
    List<OrdersDTO> getAllOrder(Connection connection)throws Exception;
}
