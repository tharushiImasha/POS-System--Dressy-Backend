package lk.ijse.gdse68.clothingpos.dao.custom.impl;

import lk.ijse.gdse68.clothingpos.dao.SqlUtil;
import lk.ijse.gdse68.clothingpos.dao.custom.CustomerDAO;
import lk.ijse.gdse68.clothingpos.dto.CustomerDTO;
import lk.ijse.gdse68.clothingpos.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public static String SAVE_CUSTOMER = "INSERT INTO customer (cus_id, name, email, address, phone) VALUES (?,?,?,?,?)";
    public static String GET_CUSTOMER = "SELECT * FROM customer";
    public static String DELETE_CUSTOMER = "DELETE FROM customer WHERE cus_id=?";
    public static String UPDATE_CUSTOMER = "UPDATE customer SET name=?,email=?,address=?,phone=? WHERE cus_id=?";

    @Override
    public boolean save(Connection connection, Customer entity) throws SQLException {
        return SqlUtil.execute(connection, SAVE_CUSTOMER, entity.getCus_id(), entity.getName(),entity.getEmail(), entity.getAddress(), entity.getPhone());
    }

    @Override
    public boolean update(Connection connection, Customer entity) throws SQLException {
        return  SqlUtil.execute(connection, UPDATE_CUSTOMER, entity.getName(), entity.getEmail(), entity.getAddress(), entity.getPhone(), entity.getCus_id());
    }

    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        ResultSet rst = SqlUtil.execute(connection, GET_CUSTOMER);
        System.out.println(customerList);
        while(rst.next()){
            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );

            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public boolean delete(Connection connection, String id) throws SQLException {
        return SqlUtil.execute(connection, DELETE_CUSTOMER, id);
    }

}
