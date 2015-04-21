package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBPuntoDeVenta;
import model.PuntoDeVenta;

import java.util.List;

/**
 * Created by Ivan on 06/04/2015.
 */
public class PuntoDeVentaRepositorio extends Repositorio<PuntoDeVenta, Long> {
    PersistenciaDB<PuntoDeVenta, Long> dao;

    public PuntoDeVentaRepositorio(PersistenciaDBPuntoDeVenta dao) {
        this.dao = dao;
    }

    public void crear(PuntoDeVenta pdv){
        dao.create(pdv);
    }

    public List<PuntoDeVenta> listarTodo() {
        return dao.listAll();
    }

    public void modificar (PuntoDeVenta pdv) { dao.update(pdv);}
}
