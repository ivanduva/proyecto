package dao;

import model.EstadoEncomienda;
import play.db.ebean.Model;

/**
 * Created by Ivan on 12/05/2015.
 */
public class PersistenciaDBEstadoEncomienda extends PersistenciaDB<EstadoEncomienda, Long> {

    public static final Model.Finder<Long, EstadoEncomienda> find = new Model.Finder<Long, EstadoEncomienda>(Long.class,
            EstadoEncomienda.class);

    public PersistenciaDBEstadoEncomienda() {
        super(EstadoEncomienda.class);
    }

    @Override
    public EstadoEncomienda findByName(String s) {
        return find.where().eq("nombre", s).findUnique();
    }
}
