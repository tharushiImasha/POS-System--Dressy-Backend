package lk.ijse.gdse68.clothingpos.bo.custom.impl;

import lk.ijse.gdse68.clothingpos.bo.custom.CustomerBO;
import lk.ijse.gdse68.clothingpos.dao.DAOFactory;
import lk.ijse.gdse68.clothingpos.dao.custom.CustomerDAO;
import lk.ijse.gdse68.clothingpos.dto.CustomerDTO;
import lk.ijse.gdse68.clothingpos.entity.Customer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getSuperDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(Connection connection, CustomerDTO customerDTO) throws Exception {
        return customerDAO.save(connection, new Customer(customerDTO.getCus_id(), customerDTO.getName(), customerDTO.getEmail(), customerDTO.getAddress(), customerDTO.getPhone()));
    }

    @Override
    public boolean updateCustomer(Connection connection, String id, CustomerDTO customerDTO) throws Exception {
        return customerDAO.update(connection, new Customer(customerDTO.getCus_id(), customerDTO.getName(), customerDTO.getEmail(), customerDTO.getAddress(), customerDTO.getPhone()));
    }

    @Override
    public boolean deleteCustomer( Connection connection, String id) throws Exception {
        return customerDAO.delete(connection, id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws Exception {
        List<Customer> customers = customerDAO.getAll(connection);
        List<CustomerDTO> customerDtos = new ArrayList<>();

        for (Customer customer: customers) {
            customerDtos.add(new CustomerDTO(customer.getCus_id(), customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhone()));
        }
        return customerDtos;
    }
}
