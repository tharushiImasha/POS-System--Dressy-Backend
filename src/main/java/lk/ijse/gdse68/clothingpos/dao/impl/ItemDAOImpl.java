package lk.ijse.gdse68.clothingpos.dao.impl;

import lk.ijse.gdse68.clothingpos.dao.ItemDAO;
import lk.ijse.gdse68.clothingpos.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    public static String SAVE_ITEM = "INSERT INTO item (costume_id, type, color, amount, price) VALUES (?,?,?,?,?)";
    public static String GET_ITEM= "SELECT * FROM item";
    public static String DELETE_ITEM = "DELETE FROM item WHERE costume_id=?";
    public static String UPDATE_ITEM = "UPDATE item SET type=?,color=?,amount=?,price=? WHERE costume_id=?";

    @Override
    public String saveItem(ItemDTO itemDTO, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(SAVE_ITEM);

            ps.setString(1, itemDTO.getCostume_id());
            ps.setString(2, itemDTO.getType());
            ps.setString(3, itemDTO.getColor());
            ps.setString(4, String.valueOf(itemDTO.getAmount()));
            ps.setString(5, itemDTO.getPrice());

            if (ps.executeUpdate() != 0) {
                return "Item save successfully";
            }else {
                return "Save Item failed";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean updateItem(String id, ItemDTO itemDTO, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(UPDATE_ITEM);
            ps.setString(1, itemDTO.getType());
            ps.setString(2, itemDTO.getColor());
            ps.setString(3, String.valueOf(itemDTO.getAmount()));
            ps.setString(4, itemDTO.getPrice());
            ps.setString(5, id);

            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean deleteItem(String id, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(DELETE_ITEM);
            ps.setString(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) throws Exception {

        List<ItemDTO> itemDTOS = new ArrayList<>();

        try {

            var ps = connection.prepareStatement(GET_ITEM);
            var rst = ps.executeQuery();

            while (rst.next()){
                ItemDTO itemDTO = new ItemDTO();

                itemDTO.setCostume_id(rst.getString("costume_id"));
                itemDTO.setType(rst.getString("type"));
                itemDTO.setColor(rst.getString("color"));
                itemDTO.setAmount(rst.getInt("amount"));
                itemDTO.setPrice(rst.getString("price"));

                itemDTOS.add(itemDTO);
            }

            return itemDTOS;

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
