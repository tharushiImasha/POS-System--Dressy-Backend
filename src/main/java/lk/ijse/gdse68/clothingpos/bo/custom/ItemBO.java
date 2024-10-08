package lk.ijse.gdse68.clothingpos.bo.custom;

import lk.ijse.gdse68.clothingpos.bo.SuperBO;
import lk.ijse.gdse68.clothingpos.dto.ItemDTO;

import java.sql.Connection;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO itemDTO, Connection connection)throws Exception;
    boolean updateItem(String id, ItemDTO itemDTO, Connection connection)throws Exception;
    boolean deleteItem(String id, Connection connection)throws Exception;
    List<ItemDTO> getAllItems(Connection connection)throws Exception;
}
