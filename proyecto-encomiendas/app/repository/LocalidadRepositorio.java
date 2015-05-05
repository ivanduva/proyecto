package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBLocalidad;
import model.Localidad;

/**
 * Created by rodrigo on 5/4/15.
 */
public class LocalidadRepositorio extends Repositorio<Localidad, Long> {

    PersistenciaDB<Localidad, Long> dao;

    public LocalidadRepositorio(PersistenciaDBLocalidad dao) {
        super(dao);
    }
}
