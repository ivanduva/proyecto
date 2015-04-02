package dao;

import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
public interface Persistencia<T, K> {
    public void create(T t);
    public T get(K k);
    public void update(T t);
    public void delete(T t);
    public List<T> listAll();
}
