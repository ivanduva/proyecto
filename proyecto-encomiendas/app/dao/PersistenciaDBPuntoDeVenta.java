package dao;

import model.PuntoDeVenta;

/**
 * Created by Ivan on 13/04/2015.
 */
public class PersistenciaDBPuntoDeVenta extends PersistenciaDB<PuntoDeVenta, Long> {


    public PersistenciaDBPuntoDeVenta() {
        super(PuntoDeVenta.class);
    }
}
