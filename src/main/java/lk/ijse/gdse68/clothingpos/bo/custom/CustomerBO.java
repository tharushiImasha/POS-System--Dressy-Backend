package lk.ijse.gdse68.clothingpos.bo.custom;

import lk.ijse.gdse68.clothingpos.bo.SuperBO;
import lk.ijse.gdse68.clothingpos.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(Connection connection, CustomerDTO customerDTO)throws Exception;
    boolean updateCustomer( Connection connection, String id, CustomerDTO customerDTO)throws Exception;
    boolean deleteCustomer( Connection connection, String id)throws Exception;
    List<CustomerDTO> getAllCustomers(Connection connection)throws Exception;
}
