package dao;

import model.Venta;

/**
 * Created by Ivan on 14/05/2015.
 */
public class PersistenciaDBVenta extends PersistenciaDB<Venta, Long> {

    public PersistenciaDBVenta() {
        super(Venta.class);
    }
}
