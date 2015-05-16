package dao;


import com.avaje.ebean.Ebean;

import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
public class PersistenciaDB<T, K> implements Persistencia<T, K> {

    private Class<T> type;

    public PersistenciaDB(Class<T> classType) {
        type = classType;
    }

    @Override
    public void create(T t) {
        Ebean.save(t);
    }

    @Override
    public T get(K k) {
        return Ebean.find(type, k);
    }

    @Override
    public void update(T t) {
        Ebean.update(t);
    }

    @Override
    public void delete(T t) {
        Ebean.delete(t);
    }

    @Override
    public List<T> listAll() {
        return Ebean.find(type).findList();
    }

    @Override
    public T findByName(String s) {
        return null;
    }

    @Override
    public int findRowCount() {
        return 0;
    }

    @Override
    public T findByAuthToken(String s) {
        return null;
    }

    @Override
    public List<T> listSome(String s1, String s2) {
        return null;
    }

}
