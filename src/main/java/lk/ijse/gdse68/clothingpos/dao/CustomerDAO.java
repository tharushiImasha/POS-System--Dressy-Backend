package lk.ijse.gdse68.clothingpos.dao;

import lk.ijse.gdse68.clothingpos.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public interface CustomerDAO {
    String saveCustomer(CustomerDTO customerDTO, Connection connection)throws Exception;
    boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection)throws Exception;
    boolean deleteCustomer(String id, Connection connection)throws Exception;
    List<CustomerDTO> getAllCustomers(Connection connection)throws Exception;
}
