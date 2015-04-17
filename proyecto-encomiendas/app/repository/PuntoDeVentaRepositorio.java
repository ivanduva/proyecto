package repository;

import dao.PersistenciaDBPuntoDeVenta;
import model.PuntoDeVenta;

/**
 * Created by Ivan on 06/04/2015.
 */
public class PuntoDeVentaRepositorio extends Repositorio<PuntoDeVenta, Long> {
    PersistenciaDBPuntoDeVenta dao;

    public PuntoDeVentaRepositorio(PersistenciaDBPuntoDeVenta persistenciaDBPuntoDeVenta) {
        this.dao = persistenciaDBPuntoDeVenta;
    }

    public void crear (PuntoDeVenta pdv) {
        dao.create(pdv);
    }
}
