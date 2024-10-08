package lk.ijse.gdse68.clothingpos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(Connection connection, T entity) throws SQLException;

    boolean update (Connection connection, T entity) throws SQLException;

    ArrayList<T> getAll (Connection connection) throws SQLException;

    boolean delete (Connection connection, String id) throws SQLException;
}
