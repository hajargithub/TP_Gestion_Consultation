package ma.enset.gestionconsultations.dao;

import java.util.List;

public interface Dao<E,I> {
    List<E> findALL();
    E findById(I id);
    void add(E e);
    void deleteById(I id);
    void update(E e);

}