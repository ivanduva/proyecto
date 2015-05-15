package dao;

import model.Venta;
import play.db.ebean.Model;

/**
 * Created by Ivan on 14/05/2015.
 */
public class PersistenciaDBVenta extends PersistenciaDB<Venta, Long> {

    public static final Model.Finder<Long, Venta> find = new Model.Finder<Long, Venta>(Long.class,
            Venta.class);

    public PersistenciaDBVenta() {
        super(Venta.class);
    }


}
