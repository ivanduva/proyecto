package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBSecurityRole;
import model.SecurityRole;

/**
 * Created by Ivan on 01/05/2015.
 */
public class SecurityRoleRepositorio extends Repositorio<SecurityRole, Long> {

    PersistenciaDB<SecurityRole, Long> dao;

    public SecurityRoleRepositorio(PersistenciaDBSecurityRole dao) {
        super(dao);
    }
}
