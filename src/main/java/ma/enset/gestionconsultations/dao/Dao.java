package ma.enset.gestionconsultations.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<E,I> {
    List<E> findALL();
    E findById(I id) throws SQLException;
    void add(E e);
    void deleteById(I id);
    void update(E e) throws SQLException;

}