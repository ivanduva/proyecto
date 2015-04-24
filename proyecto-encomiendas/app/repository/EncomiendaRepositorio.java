package repository;

import dao.Persistencia;
import model.Encomienda;

/**
 * Created by Ivan on 02/04/2015.
 */
public class EncomiendaRepositorio extends Repositorio<Encomienda, Long> {

    public EncomiendaRepositorio(Persistencia dao) {
        super(dao);
    }


}
