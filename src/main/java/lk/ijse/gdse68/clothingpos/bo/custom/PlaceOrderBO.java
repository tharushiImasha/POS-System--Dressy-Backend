package lk.ijse.gdse68.clothingpos.bo.custom;

import lk.ijse.gdse68.clothingpos.bo.SuperBO;
import lk.ijse.gdse68.clothingpos.dto.OrdersDTO;

import java.sql.Connection;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    boolean saveOrder(OrdersDTO ordersDTO, Connection connection)throws Exception;
    List<OrdersDTO> getAllOrder(Connection connection)throws Exception;
}
