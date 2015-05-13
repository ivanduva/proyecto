package repository;

import dao.Persistencia;

import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
public abstract class Repositorio<T, K> {
    private Persistencia<T, K> dao;

    public Repositorio(Persistencia<T, K> dao) {
        this.dao = dao;
    }

    public void crear(T t) {
        dao.create(t);
    }

    public T buscarPorId(K k) {
        return (T) dao.get(k);
    }

    public T buscarPorNombre(String s){
        return (T) dao.findByName(s);
    }

    public int conteoDeColumnas(){
        return dao.findRowCount();
    }

    public void eliminar(T t) {
       // T t = (T) dao.get(k);
        dao.delete(t);
    }

    public List<T> listarTodo() {
        return dao.listAll();
    }

    public List<T> listarAlgunos(String s1, String s2) { return dao.listSome(s1, s2); }

    public void modificar(T t) {
        dao.update(t);
    }

    public Persistencia getDao() {
        return dao;
    }
}
