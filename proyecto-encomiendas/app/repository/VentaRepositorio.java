package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBVenta;
import model.Venta;

/**
 * Created by Ivan on 05/04/2015.
 */
public class VentaRepositorio extends Repositorio<Venta, Long> {

    PersistenciaDB<Venta, Long> dao;

    public VentaRepositorio(PersistenciaDBVenta dao) {
        super(dao);
    }
}
