package by.javaguru.je.jdbc.servise;

import java.sql.SQLException;
import java.util.List;

public interface GenericService<T, ID> {
    T findById(ID id);
    List<T> findAll();
    T insert(T t) throws SQLException;
    T update(T t, ID id) throws SQLException;
    T delete(ID id) throws SQLException;
}
