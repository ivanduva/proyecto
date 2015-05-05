package dao;

import model.Localidad;

/**
 * Created by rodrigo on 5/4/15.
 */
public class PersistenciaDBLocalidad extends PersistenciaDB<Localidad, Long> {

    public PersistenciaDBLocalidad() {
        super(Localidad.class);
    }
}
