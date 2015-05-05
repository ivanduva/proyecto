import dao.PersistenciaDBLocalidad;
import dao.PersistenciaDBSecurityRole;
import dao.PersistenciaDBUserPermission;
import dao.PersistenciaDBUsuario;
import model.Localidad;
import model.SecurityRole;
import model.UserPermission;
import model.Usuario;
import play.Application;
import play.GlobalSettings;
import repository.LocalidadRepositorio;
import repository.SecurityRoleRepositorio;
import repository.UserPermissionRepositorio;
import repository.UsuarioRepositorio;
import security.TipoPermiso;
import security.TipoUsuario;

import java.util.Date;

/**
 * Created by Ivan on 01/05/2015.
 */
//Acciones que va a realizar la app al cargar
public class Global extends GlobalSettings {


    public void onStart (Application application){

        UsuarioRepositorio repositorioUsuario = new UsuarioRepositorio(new PersistenciaDBUsuario());
        SecurityRoleRepositorio repositorioRol = new SecurityRoleRepositorio(new PersistenciaDBSecurityRole());
        UserPermissionRepositorio repositorioPermiso = new UserPermissionRepositorio(new PersistenciaDBUserPermission());
        LocalidadRepositorio repositorioLocalidad = new LocalidadRepositorio(new PersistenciaDBLocalidad());

        //Agrega a la base los roles (si no existen en la base)
        if (repositorioRol.conteoDeColumnas() == 0){

            for (TipoUsuario name: TipoUsuario.values()){

                SecurityRole rol = new SecurityRole();
                rol.setName(name.toString());
                repositorioRol.crear(rol);
            }
        }

        //Agrega a la base los permisos (si no existen en la base)
        if (repositorioPermiso.conteoDeColumnas() == 0){

            for (TipoPermiso value : TipoPermiso.values()){

                UserPermission permiso = new UserPermission();
                permiso.setValue(value.toString());
                repositorioPermiso.crear(permiso);
            }

        }

        if (repositorioLocalidad.conteoDeColumnas() == 0){
            Localidad localidad = new Localidad("La Plata", (long) 1900);
            repositorioLocalidad.crear(localidad);
        }

        //Crea un usuario admin
        if (repositorioUsuario.conteoDeColumnas() == 0){

            Usuario admin = new Usuario("admin", new Date());
            admin.setPassword("admin1234");
            admin.agregarRol(repositorioRol.buscarPorNombre("ADMINISTRATIVO"));
      //      admin.agregarPermiso(repositorioPermiso.buscarPorNombre("PERMISO_1"));
        //    admin.agregarPermiso(repositorioPermiso.buscarPorNombre("PERMISO_2"));
            repositorioUsuario.crear(admin);
        }

        super.onStart(application);

    }
}
