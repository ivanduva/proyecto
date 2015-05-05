package dao;

import model.Localidad;

import java.util.List;

/**
 * Created by rodrigo on 5/4/15.
 */
public class PersistenciaDBLocalidad extends PersistenciaDB<Localidad, Long> {

    public PersistenciaDBLocalidad(Class<Localidad> classType) {
        super(classType);
    }
}
