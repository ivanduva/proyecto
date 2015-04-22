package repository;

import dao.Persistencia;
import model.Servicio;

/**
 * Created by Ivan on 05/04/2015.
 */
public class ServicioRepositorio extends Repositorio<Servicio, Long> {

    public ServicioRepositorio(Persistencia<Servicio, Long> dao) {
        super(dao);
    }
}
