package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBEstadoEncomienda;
import model.EstadoEncomienda;

/**
 * Created by Ivan on 12/05/2015.
 */
public class EstadoEncomiendaRepositorio extends Repositorio<EstadoEncomienda, Long> {

    PersistenciaDB<EstadoEncomienda, Long> dao;

    public EstadoEncomiendaRepositorio(PersistenciaDBEstadoEncomienda dao) {
        super(dao);
    }
}
