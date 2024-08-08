package lk.ijse.gdse68.clothingpos.dao.custom.impl;

import lk.ijse.gdse68.clothingpos.dao.SqlUtil;
import lk.ijse.gdse68.clothingpos.dao.custom.OrdersDAO;
import lk.ijse.gdse68.clothingpos.entity.Orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {

    public static String SAVE_ORDER = "INSERT INTO orders (order_id, cus_id, total, date) VALUES (?,?,?,?)";
    public static String GET_ORDER = "SELECT * FROM orders";

    @Override
    public boolean save(Connection connection, Orders entity) throws SQLException {
        return SqlUtil.execute(connection, SAVE_ORDER, entity.getOrder_id(), entity.getCus_id(), entity.getTotal(), entity.getDate());
    }

    @Override
    public boolean update(Connection connection, Orders entity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Orders> getAll(Connection connection) throws SQLException {
        ArrayList<Orders> ordersList = new ArrayList<Orders>();
        ResultSet rst = SqlUtil.execute(connection, GET_ORDER);
        System.out.println(ordersList);
        while(rst.next()){
            Orders orders = new Orders(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDate(4).toLocalDate()
            );

            ordersList.add(orders);
        }
        return ordersList;
    }

    @Override
    public boolean delete(Connection connection, String id) throws SQLException {
        return false;
    }
}
