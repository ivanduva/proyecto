import dao.*;
import model.*;
import play.Application;
import play.GlobalSettings;
import repository.*;
import security.TipoPermiso;
import security.TipoUsuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 01/05/2015.
 */
//Acciones que va a realizar la app al cargar
public class Global extends GlobalSettings {

    @Override
    public void onStart (Application application){


        UsuarioRepositorio repositorioUsuario = new UsuarioRepositorio(new PersistenciaDBUsuario());
        SecurityRoleRepositorio repositorioRol = new SecurityRoleRepositorio(new PersistenciaDBSecurityRole());
        UserPermissionRepositorio repositorioPermiso = new UserPermissionRepositorio(new PersistenciaDBUserPermission());
        LocalidadRepositorio repositorioLocalidad = new LocalidadRepositorio(new PersistenciaDBLocalidad());
        PersonaRepositorio repositorioPersona = new PersonaRepositorio(new PersistenciaDBPersona());
        PuntoDeVentaRepositorio puntoDeVentaRepositorio = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());
        VentaRepositorio ventaRepositorio = new VentaRepositorio(new PersistenciaDBVenta());

        Localidad laPlata = null;
        Localidad berisso = null;
        Usuario admin = null;
        Usuario luke = null;
        Usuario darth = null;
        Usuario cachito = null;
        Usuario loDeCacho = null;
        PuntoDeVenta puntoDeVenta = null;
        Cliente cliente = null;

        //Agrega a la base los roles (si no existen en la base)
        if (repositorioRol.listarTodo().isEmpty()){

            for (TipoUsuario name: TipoUsuario.values()){

                SecurityRole rol = new SecurityRole();
                rol.setName(name.toString());
                repositorioRol.crear(rol);
            }
        }

        //Agrega a la base los permisos (si no existen en la base)
        if (repositorioPermiso.listarTodo().isEmpty()){

            for (TipoPermiso value : TipoPermiso.values()){

                UserPermission permiso = new UserPermission();
                permiso.setValue(value.toString());
                repositorioPermiso.crear(permiso);
            }

        }

        if (repositorioLocalidad.listarTodo().isEmpty()){
            laPlata = new Localidad("La Plata", 1900L);
            repositorioLocalidad.crear(laPlata);

            berisso = new Localidad("Berisso", 1920L);
            repositorioLocalidad.crear(berisso);

        }

        //Crea un usuario admin
        if (repositorioUsuario.listarTodo().isEmpty()){

            admin = new Usuario("admin", new Date());
            admin.setPassword("admin1234");
            admin.agregarRol(repositorioRol.buscarPorNombre("ADMINISTRATIVO"));
            repositorioUsuario.crear(admin);
        }

        if (repositorioPersona.listarTodo().isEmpty()) {

            darth = new Usuario("darth", new Date());
            darth.setPassword("darth1234");
            darth.agregarRol(repositorioRol.buscarPorNombre("CLIENTE"));
            repositorioUsuario.crear(darth);

            luke = new Usuario("luke", new Date());
            luke.setPassword("luke1234");
            luke.agregarRol(repositorioRol.buscarPorNombre("CLIENTE"));
            repositorioUsuario.crear(luke);

            cliente = new Cliente("soytupadre@lafuerza.com", new Date(), laPlata, "Darth Vader", "444444",
                    "A", "NO", 0);
            cliente.setUsuario(darth);

            repositorioPersona.crear(cliente);

            cliente = new Cliente("luke@lafuerza.com", new Date(), berisso, "Luke Skywalker", "444444", "A", "NO", 0);
            cliente.setUsuario(luke);
            repositorioPersona.crear(cliente);
        }

        if (puntoDeVentaRepositorio.listarTodo().isEmpty()) {

            cachito = new Usuario("cachito", new Date());
            cachito.setPassword("cachito1234");
            cachito.agregarRol(repositorioRol.buscarPorNombre("VENDEDOR"));
            repositorioUsuario.crear(cachito);

            loDeCacho = new Usuario("cacho", new Date());
            loDeCacho.setPassword("cacho1234");
            loDeCacho.agregarRol(repositorioRol.buscarPorNombre("VENDEDOR"));

            puntoDeVenta = new PuntoDeVenta("calle falsa 123", "falso@email.com", laPlata, "Cachito",
                    "Cacho", "4444", cachito, TipoPunto.PUNTO_EXTERNO);
            puntoDeVentaRepositorio.crear(puntoDeVenta);

            puntoDeVenta = new PuntoDeVenta("calle falsa 321", "email@falso.com", laPlata, "Lo de Cacho",
                    "Cacho", "4444", loDeCacho, TipoPunto.OFICINA_ADMINISTRATIVA);

            puntoDeVentaRepositorio.crear(puntoDeVenta);
        }

        if (ventaRepositorio.listarTodo().isEmpty()) {
            List<Encomienda> encomiendas = new ArrayList<Encomienda>();
            List<EstadoEncomienda> estadoEncomiendas = new ArrayList<EstadoEncomienda>();

            EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date(), puntoDeVenta);
            estadoEncomienda.setNombreEnSucursal();

            estadoEncomiendas.add(estadoEncomienda);

            encomiendas.add(new Encomienda("alguien", "123", new Date(), estadoEncomiendas, cliente, laPlata, 100L));

            Venta venta = new Venta(new Date(), 100L, encomiendas, cliente, puntoDeVenta);
            ventaRepositorio.crear(venta);
        }

        super.onStart(application);

    }
}
