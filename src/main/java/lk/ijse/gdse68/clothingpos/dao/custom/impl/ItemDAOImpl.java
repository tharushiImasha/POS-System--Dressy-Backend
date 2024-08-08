package lk.ijse.gdse68.clothingpos.dao.custom.impl;

import lk.ijse.gdse68.clothingpos.dao.SqlUtil;
import lk.ijse.gdse68.clothingpos.dao.custom.ItemDAO;
import lk.ijse.gdse68.clothingpos.dto.ItemDTO;
import lk.ijse.gdse68.clothingpos.entity.Customer;
import lk.ijse.gdse68.clothingpos.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    public static String SAVE_ITEM = "INSERT INTO item (costume_id, type, color, amount, price) VALUES (?,?,?,?,?)";
    public static String GET_ITEM= "SELECT * FROM item";
    public static String DELETE_ITEM = "DELETE FROM item WHERE costume_id=?";
    public static String UPDATE_ITEM = "UPDATE item SET type=?,color=?,amount=?,price=? WHERE costume_id=?";

    @Override
    public boolean save(Connection connection, Item entity) throws SQLException {
        return SqlUtil.execute(connection, SAVE_ITEM, entity.getCostume_id(), entity.getType(), entity.getColor(), entity.getAmount(), entity.getPrice());
    }

    @Override
    public boolean update(Connection connection, Item entity) throws SQLException {
        return SqlUtil.execute(connection, UPDATE_ITEM, entity.getType(), entity.getColor(), entity.getAmount(), entity.getPrice(), entity.getCostume_id());
    }

    @Override
    public ArrayList<Item> getAll(Connection connection) throws SQLException {
        ArrayList<Item> itemList = new ArrayList<Item>();
        ResultSet rst = SqlUtil.execute(connection, GET_ITEM);
        System.out.println(itemList);
        while(rst.next()){
            Item item = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5)
            );

            itemList.add(item);
        }
        return itemList;
    }

    @Override
    public boolean delete(Connection connection, String id) throws SQLException {
        return SqlUtil.execute(connection, DELETE_ITEM, id);
    }
}
