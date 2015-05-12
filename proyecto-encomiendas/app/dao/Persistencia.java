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
    public T findByName(String s);
    public int findRowCount();
    public T findByAuthToken(String s);
    public List<T> listSome(String s1, String s2);
}
