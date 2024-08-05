package lk.ijse.gdse68.clothingpos.bo.impl;

import lk.ijse.gdse68.clothingpos.bo.CustomerBO;
import lk.ijse.gdse68.clothingpos.dao.impl.CustomerDAOImpl;
import lk.ijse.gdse68.clothingpos.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    @Override
    public String saveCustomer(CustomerDTO customerDTO, Connection connection) throws Exception {
        return customerDAO.saveCustomer(customerDTO, connection);
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection) throws Exception {
        return customerDAO.updateCustomer(id, customerDTO, connection);
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws Exception {
        return customerDAO.deleteCustomer(id, connection);
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws Exception {
        return customerDAO.getAllCustomers(connection);
    }
}
