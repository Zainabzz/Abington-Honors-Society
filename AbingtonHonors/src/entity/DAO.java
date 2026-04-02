package entity;


import java.util.List;
import java.util.Optional;

/**
 * @author aramc
 * @param <T>
 */
public interface DAO<T>
{
    Optional<T> get(int id);
    List<T> getAll();
    void insert(T t);
    void update(T t);
    void delete(T t);
    List<String> getColumnNames();
}
