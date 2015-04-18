package dao;


import play.db.ebean.Model;

import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
public class PersistenciaDB<T,K> implements Persistencia<T,K>{
    @Override
    public void create(T t) {
        T.save();
    }

    @Override
    public T get(K k) {

        T t = new Model.Finder(String.class, T.class).byId(K);

        return t;
    }

    @Override
    public void update(T t) {
        T.update();
    }

    @Override
    public void delete(T t) {
        T.delete();
    }

    @Override
    public List<T> listAll() {

        List<T> list = new Model.Finder(String.class, T.class).all();

        return list;
    }
}
