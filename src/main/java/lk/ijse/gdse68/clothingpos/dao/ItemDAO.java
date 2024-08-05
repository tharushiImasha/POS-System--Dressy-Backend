package lk.ijse.gdse68.clothingpos.dao;

import lk.ijse.gdse68.clothingpos.dto.CustomerDTO;
import lk.ijse.gdse68.clothingpos.dto.ItemDTO;

import java.sql.Connection;
import java.util.List;

public interface ItemDAO {
    String saveItem(ItemDTO itemDTO, Connection connection)throws Exception;
    boolean updateItem(String id, ItemDTO itemDTO, Connection connection)throws Exception;
    boolean deleteItem(String id, Connection connection)throws Exception;
    List<ItemDTO> getAllItems(Connection connection)throws Exception;
}
