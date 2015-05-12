package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBEncomienda;
import model.Encomienda;

/**
 * Created by Ivan on 02/04/2015.
 */
public class EncomiendaRepositorio extends Repositorio<Encomienda, Long> {

    PersistenciaDB<Encomienda, Long> dao;

    public EncomiendaRepositorio(PersistenciaDBEncomienda dao) {
        super(dao);
    }


}
