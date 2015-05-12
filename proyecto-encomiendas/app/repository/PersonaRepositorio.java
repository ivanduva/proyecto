package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBPersona;
import model.Persona;

/**
 * Created by Ivan on 12/05/2015.
 */
public class PersonaRepositorio extends Repositorio<Persona, Long> {

    PersistenciaDB<Persona, Long> dao;

    public PersonaRepositorio(PersistenciaDBPersona dao) {
        super(dao);
    }


}
