package dao;

import model.Encomienda;

/**
 * Created by Ivan on 12/05/2015.
 */
public class PersistenciaDBEncomienda extends PersistenciaDB<Encomienda, Long> {


    public PersistenciaDBEncomienda() {
        super(Encomienda.class);
    }

}
