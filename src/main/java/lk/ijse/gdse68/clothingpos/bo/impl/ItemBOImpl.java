package lk.ijse.gdse68.clothingpos.bo.impl;

import lk.ijse.gdse68.clothingpos.bo.ItemBO;
import lk.ijse.gdse68.clothingpos.dao.impl.ItemDAOImpl;
import lk.ijse.gdse68.clothingpos.dto.ItemDTO;

import java.sql.Connection;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAOImpl itemDAO = new ItemDAOImpl();


    @Override
    public String saveItem(ItemDTO itemDTO, Connection connection) throws Exception {
        return itemDAO.saveItem(itemDTO, connection);
    }

    @Override
    public boolean updateItem(String id, ItemDTO itemDTO, Connection connection) throws Exception {
        return itemDAO.updateItem(id, itemDTO, connection);
    }

    @Override
    public boolean deleteItem(String id, Connection connection) throws Exception {
        return itemDAO.deleteItem(id, connection);
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) throws Exception {
        return itemDAO.getAllItems(connection);
    }
}
