package dao;

import model.UserPermission;
import play.db.ebean.Model;

/**
 * Created by Ivan on 01/05/2015.
 */
public class PersistenciaDBUserPermission extends PersistenciaDB<UserPermission, Long>{

    public static final Model.Finder<Long, UserPermission> find = new Model.Finder<Long, UserPermission>(Long.class,
            UserPermission.class);

    public PersistenciaDBUserPermission() {
        super(UserPermission.class);
    }

    public UserPermission findByValue(String value)
    {
        return find.where()
                .eq("value",
                        value)
                .findUnique();
    }


}
