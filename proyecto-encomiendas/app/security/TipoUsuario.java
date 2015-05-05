package security;

import be.objectify.deadbolt.core.models.Role;

/**
 * Created by Ivan on 05/04/2015.
 */
//El/los rol/es que va a tener un usuario
public enum TipoUsuario implements Role{
    CLIENTE,
    GERENTE,
    VENDEDOR,
    EMPLEADO,
    MECANICO,
    ADMINISTRATIVO;



    public String getName(){

        return name().toString();
    }

}
