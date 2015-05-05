package dao;

import model.SecurityRole;
import play.db.ebean.Model;

/**
 * Created by Ivan on 01/05/2015.
 */
public class PersistenciaDBSecurityRole extends PersistenciaDB<SecurityRole, Long> {

    public static final Model.Finder<Long, SecurityRole> find = new Model.Finder<Long, SecurityRole>(Long.class,
            SecurityRole.class);

    public PersistenciaDBSecurityRole() {
        super(SecurityRole.class);
    }

    public SecurityRole findByName(String s)
    {
        return find.where()
                .eq("name",
                        s)
                .findUnique();
    }
}
