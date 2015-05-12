package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dao.PersistenciaDBPersona;
import dao.PersistenciaDBSecurityRole;
import dao.PersistenciaDBUsuario;
import model.Cliente;
import model.Empleado;
import model.Persona;
import model.Usuario;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.PersonaRepositorio;
import repository.SecurityRoleRepositorio;
import repository.UsuarioRepositorio;

import java.util.Date;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by Ivan on 12/05/2015.
 */
public class PersonaController extends Controller {

    static UsuarioRepositorio repositorioUsuario = new UsuarioRepositorio(new PersistenciaDBUsuario());
    static PersonaRepositorio repositorioPersona = new PersonaRepositorio(new PersistenciaDBPersona());
    static SecurityRoleRepositorio repositorioRol = new SecurityRoleRepositorio(new PersistenciaDBSecurityRole());

    //Lo usamos para cuando se registra el cliente por su cuenta y cuando lo registra el ADMIN o el VENDEDOR. Cuando lo
    //registra el ADMIN o el VENDEDOR, puede elegir si "Puede reservar". Si se registra el cliente por su cuenta, no.
    public static Result crearCliente() {

        JsonNode json = request().body().asJson();
        Cliente cliente = Json.fromJson(json, Cliente.class);
        Usuario usuario = Json.fromJson(json, Usuario.class);
        usuario.setFechaCreacion(new Date());
        usuario.agregarRol(repositorioRol.buscarPorNombre("CLIENTE"));

        if (cliente.getPuedeReservar() == null) {
            cliente.setPuedeReservar("NO");
        }
        cliente.setPuntosViajero(0);
        cliente.setUsuario(usuario);

        repositorioPersona.crear(cliente);
        repositorioUsuario.crear(usuario);

        return ok(toJson(cliente));
    }

    public static Result crearEmpleado() {

        JsonNode json = request().body().asJson();
        Empleado empleado = Json.fromJson(json, Empleado.class);
        Usuario usuario = Json.fromJson(json, Usuario.class);
        usuario.setFechaCreacion(new Date());
        usuario.agregarRol(repositorioRol.buscarPorNombre("EMPLEADO"));
        empleado.setCesadoFalse();
        empleado.setUsuario(usuario);

        repositorioPersona.crear(empleado);
        repositorioUsuario.crear(usuario);

        return ok(toJson(empleado));
    }

    public static Result getPersona(Long id) {

        Persona persona = repositorioPersona.buscarPorId(id);

        return ok(toJson(persona));
    }

    public static Result modificarPersona(Long id) {

        JsonNode json = request().body().asJson();
        Persona persona = Json.fromJson(json, Persona.class);
        repositorioPersona.modificar(persona);

        return ok();
    }

    public static Result listarClientes() {

        //Ya se qué esto no es lo mejor, pero es una solución por ahora
        List<Persona> clientes = repositorioPersona.listarAlgunos("tipo", "C");
        return ok(toJson(clientes));
    }

    public static Result listarEmpleados() {

        //Ya se qué esto no es lo mejor, pero es una solución por ahora
        List<Persona> empleados = repositorioPersona.listarAlgunos("cesado", "false");
        return ok(toJson(empleados));
    }

    public static Result eliminarPersona(Long id) {

        repositorioPersona.eliminar(id);
        return ok();
    }

    public static Result cesarEmpleado(Long id) {

        Empleado empleado = (Empleado) repositorioPersona.buscarPorId(id);
        empleado.setCesadoTrue();
        repositorioPersona.modificar(empleado);

        return ok();
    }

}
