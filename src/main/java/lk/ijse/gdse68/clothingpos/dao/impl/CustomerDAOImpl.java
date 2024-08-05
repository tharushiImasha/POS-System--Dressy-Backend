package lk.ijse.gdse68.clothingpos.dao.impl;

import lk.ijse.gdse68.clothingpos.dao.CustomerDAO;
import lk.ijse.gdse68.clothingpos.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public static String SAVE_CUSTOMER = "INSERT INTO customer (cus_id, name, email, address, phone) VALUES (?,?,?,?,?)";
//    public static String GET_CUSTOMER = "SELECT * FROM customer WHERE cus_id=?";
    public static String GET_CUSTOMER = "SELECT * FROM customer";
    public static String DELETE_CUSTOMER = "DELETE FROM customer WHERE cus_id=?";
    public static String UPDATE_CUSTOMER = "UPDATE customer SET name=?,email=?,address=?,phone=? WHERE cus_id=?";

    @Override
    public String saveCustomer(CustomerDTO customerDTO, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(SAVE_CUSTOMER);

            ps.setString(1, customerDTO.getCus_id());
            ps.setString(2, customerDTO.getName());
            ps.setString(3, customerDTO.getEmail());
            ps.setString(4, customerDTO.getAddress());
            ps.setString(5, customerDTO.getPhone());

            if (ps.executeUpdate() != 0) {
                return "Customer save successfully";
            }else {
                return "Save customer failed";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(UPDATE_CUSTOMER);
            ps.setString(1, customerDTO.getName());
            ps.setString(2, customerDTO.getEmail());
            ps.setString(3, customerDTO.getAddress());
            ps.setString(4, customerDTO.getPhone());
            ps.setString(5, id);

            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws Exception {
        try {
            var ps = connection.prepareStatement(DELETE_CUSTOMER);
            ps.setString(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws Exception {

        List<CustomerDTO> customerDTOS = new ArrayList<>();

        try {

            var ps = connection.prepareStatement(GET_CUSTOMER);
            var rst = ps.executeQuery();

            while (rst.next()){
                CustomerDTO customerDTO = new CustomerDTO();

                customerDTO.setCus_id(rst.getString("cus_id"));
                customerDTO.setName(rst.getString("name"));
                customerDTO.setEmail(rst.getString("email"));
                customerDTO.setAddress(rst.getString("address"));
                customerDTO.setPhone(rst.getString("phone"));

                customerDTOS.add(customerDTO);
            }

            return customerDTOS;

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
