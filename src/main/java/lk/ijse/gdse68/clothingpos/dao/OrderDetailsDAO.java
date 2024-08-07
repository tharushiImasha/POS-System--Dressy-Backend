package lk.ijse.gdse68.clothingpos.dao;

import lk.ijse.gdse68.clothingpos.dto.OrderDetailsDTO;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailsDAO {
    String saveOrderDet(OrderDetailsDTO orderDetailsDTO, Connection connection)throws Exception;
    List<OrderDetailsDTO> getAllOrderDet(Connection connection)throws Exception;
}
