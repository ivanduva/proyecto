package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBPuntoDeVenta;
import model.PuntoDeVenta;

/**
 * Created by Ivan on 06/04/2015.
 */
public class PuntoDeVentaRepositorio extends Repositorio<PuntoDeVenta, Long> {

    PersistenciaDB<PuntoDeVenta, Long> dao;

    public PuntoDeVentaRepositorio(PersistenciaDBPuntoDeVenta dao) {
        super(dao);
    }

}
