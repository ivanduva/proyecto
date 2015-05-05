package repository;

import dao.Persistencia;
import dao.PersistenciaDB;
import model.Localidad;

/**
 * Created by rodrigo on 5/4/15.
 */
public class LocalidadRepositorio extends Repositorio<Localidad, Long> {

    public LocalidadRepositorio(Persistencia<Localidad, Long> dao) {
        super(dao);
    }
}
