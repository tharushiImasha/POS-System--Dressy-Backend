package lk.ijse.gdse68.clothingpos.dao.custom.impl;

import lk.ijse.gdse68.clothingpos.dao.SqlUtil;
import lk.ijse.gdse68.clothingpos.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse68.clothingpos.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    public static String SAVE_ORDER_DET = "INSERT INTO order_details (order_detail_id, order_id, costume_id, quantity) VALUES (?,?,?,?)";

    @Override
    public boolean save(Connection connection, OrderDetails entity) throws SQLException {
        return SqlUtil.execute(connection, SAVE_ORDER_DET, entity.getOrder_detail_id(), entity.getOrder_id(), entity.getCostume_id(), entity.getQuantity());
    }

    @Override
    public boolean update(Connection connection, OrderDetails entity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<OrderDetails> getAll(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Connection connection, String id) throws SQLException {
        return false;
    }
}
