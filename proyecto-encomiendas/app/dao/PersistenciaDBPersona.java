package dao;

import model.Persona;
import play.db.ebean.Model;

import java.util.List;

/**
 * Created by Ivan on 12/05/2015.
 */
public class PersistenciaDBPersona extends PersistenciaDB<Persona, Long> {

    public static final Model.Finder<Long, Persona> find = new Model.Finder<Long, Persona>(Long.class,
            Persona.class);

    public PersistenciaDBPersona() {
        super(Persona.class);
    }

    @Override
    public List<Persona> listSome(String s1, String s2) {
        return find.where().eq(s1, s2).findList();
    }
}
