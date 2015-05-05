package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBUserPermission;
import model.UserPermission;

/**
 * Created by Ivan on 01/05/2015.
 */
public class UserPermissionRepositorio extends Repositorio<UserPermission, Long> {

    PersistenciaDB<UserPermission, Long> dao;

    public UserPermissionRepositorio(PersistenciaDBUserPermission dao) {
        super(dao);
    }
}
