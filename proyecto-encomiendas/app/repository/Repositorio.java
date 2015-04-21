package repository;

import dao.Persistencia;

import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
public abstract class Repositorio<T, K> {
    private Persistencia<T, K> dao;

    public Repositorio() {}
    public Repositorio(Persistencia<T, K> dao) {
        this.dao = dao;
    }

    public void crear(T t) {
        dao.create(t);
    }

    public T buscarPoId(K k) {
        return (T) dao.get(k);
    }

    public void eliminar(K k) {
        T t = (T) dao.get(k);
        dao.delete(t);
    }

    public List<T> listarTodo() {
        return (List<T>) dao.listAll();
    }

    public void modificar(T t) {
        dao.update(t);
    }

    public Persistencia getDao() {
        return dao;
    }
}
