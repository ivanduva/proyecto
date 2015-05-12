package dao;

import model.PuntoDeVenta;
import play.db.ebean.Model;

/**
 * Created by Ivan on 13/04/2015.
 */
public class PersistenciaDBPuntoDeVenta extends PersistenciaDB<PuntoDeVenta, Long> {

    public static final Model.Finder<Long, PuntoDeVenta> find = new Model.Finder<Long, PuntoDeVenta>(Long.class,
            PuntoDeVenta.class);

    public PersistenciaDBPuntoDeVenta() {
        super(PuntoDeVenta.class);
    }

    @Override
    public PuntoDeVenta findByName(String s) {
        return find.where().eq("nombre", s).findUnique();
    }
}
