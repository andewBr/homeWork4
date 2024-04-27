package by.javaguru.je.jdbc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<K, E> {
    E update(E e, K k) throws SQLException;
    List<E> findAll();
    Optional<E> findById(K id);
    E save(E e) throws SQLException;
    E delete(K id) throws SQLException;
}