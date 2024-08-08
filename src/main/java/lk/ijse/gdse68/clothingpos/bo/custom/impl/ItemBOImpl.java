package lk.ijse.gdse68.clothingpos.bo.custom.impl;

import lk.ijse.gdse68.clothingpos.bo.custom.ItemBO;
import lk.ijse.gdse68.clothingpos.dao.DAOFactory;
import lk.ijse.gdse68.clothingpos.dao.custom.ItemDAO;
import lk.ijse.gdse68.clothingpos.dto.ItemDTO;
import lk.ijse.gdse68.clothingpos.entity.Item;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getSuperDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO, Connection connection) throws Exception {
        return itemDAO.save(connection, new Item(itemDTO.getCostume_id(), itemDTO.getType(), itemDTO.getColor(), itemDTO.getAmount(), itemDTO.getPrice()));
    }

    @Override
    public boolean updateItem(String id, ItemDTO itemDTO, Connection connection) throws Exception {
        return itemDAO.update(connection, new Item(id, itemDTO.getType(), itemDTO.getColor(), itemDTO.getAmount(), itemDTO.getPrice()));
    }

    @Override
    public boolean deleteItem(String id, Connection connection) throws Exception {
        return itemDAO.delete(connection, id);
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) throws Exception {
        List<Item> items = itemDAO.getAll(connection);
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item: items) {
            itemDTOS.add(new ItemDTO(item.getCostume_id(), item.getType(), item.getColor(), item.getAmount(), item.getPrice()));
        }
        return itemDTOS;
    }
}
