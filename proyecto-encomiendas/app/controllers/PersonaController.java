package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dao.PersistenciaDBPersona;
import dao.PersistenciaDBSecurityRole;
import model.Cliente;
import model.Empleado;
import model.Persona;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.PersonaRepositorio;
import repository.SecurityRoleRepositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by Ivan on 12/05/2015.
 */
public class PersonaController extends Controller {

    //static UsuarioRepositorio repositorioUsuario = new UsuarioRepositorio(new PersistenciaDBUsuario());
    static PersonaRepositorio repositorioPersona = new PersonaRepositorio(new PersistenciaDBPersona());
    static SecurityRoleRepositorio repositorioRol = new SecurityRoleRepositorio(new PersistenciaDBSecurityRole());

    //Lo usamos para cuando se registra el cliente por su cuenta y cuando lo registra el ADMIN o el VENDEDOR. Cuando lo
    //registra el ADMIN o el VENDEDOR, puede elegir si "Puede reservar". Si se registra el cliente por su cuenta, no.
    public static Result crearCliente() {

        JsonNode json = request().body().asJson();
        Cliente cliente = Json.fromJson(json, Cliente.class);
        //Usuario usuario = Json.fromJson(json, Usuario.class);
        cliente.getUsuario().setFechaCreacion(new Date());
        cliente.getUsuario().agregarRol(repositorioRol.buscarPorId((long) 1));

        if (cliente.getPuedeReservar() == null) {
            cliente.setPuedeReservar("NO");
        }
        cliente.setPuntosViajero(0);
        //cliente.setUsuario(usuario);

        repositorioPersona.crear(cliente);
        //repositorioUsuario.crear(usuario);

        return ok(toJson(cliente));
    }

    public static Result crearEmpleado() {

        JsonNode json = request().body().asJson();
        Empleado empleado = Json.fromJson(json, Empleado.class);
        //Usuario usuario = Json.fromJson(json, Usuario.class);
        empleado.getUsuario().setFechaCreacion(new Date());
        empleado.getUsuario().agregarRol(repositorioRol.buscarPorId((long) 4));
        empleado.setCesadoFalse();
        //empleado.setUsuario(usuario);

        repositorioPersona.crear(empleado);
        //repositorioUsuario.crear(usuario);

        return ok(toJson(empleado));
    }

    public static Result getPersona(Long id) {

        Persona persona = repositorioPersona.buscarPorId(id);

        return ok(toJson(persona));
    }

    public static Result modificarCliente(Long id) {

        JsonNode json = request().body().asJson();
        Cliente cliente = Json.fromJson(json, Cliente.class);
        repositorioPersona.modificar(cliente);

        return ok();
    }

    public static Result modificarEmpleado(Long id) {

        JsonNode json = request().body().asJson();
        Empleado empleado = Json.fromJson(json, Empleado.class);
        repositorioPersona.modificar(empleado);

        return ok();
    }

    public static Result listarClientes() {

        //Ya se qué esto no es lo mejor, pero es una solución por ahora
        List<Persona> clientes = repositorioPersona.listarAlgunos("tipo", "C");
        return ok(toJson(clientes));
    }

    public static Result listarEmpleados() {

        //Ya se qué esto no es lo mejor, pero es una solución por ahora
        List<Persona> empleados = repositorioPersona.listarAlgunos("tipo", "E");
        List empleadosNoCesados = new ArrayList<Persona>();
        for (int i = 0; i< empleados.size(); i++) {
            Empleado empleado = (Empleado) empleados.get(i);
            if (!( empleado.isCesado())){
                empleadosNoCesados.add(empleado);
            }
        }
        return ok(toJson(empleadosNoCesados));
    }

    public static Result eliminarCliente(Long id) {

        Cliente cliente = (Cliente) repositorioPersona.buscarPorId(id);
        repositorioPersona.eliminar(cliente);
        return ok();
    }

    public static Result cesarEmpleado(Long id) {

        Empleado empleado = (Empleado) repositorioPersona.buscarPorId(id);
        empleado.setCesadoTrue();
        repositorioPersona.modificar(empleado);

        return ok();
    }

    public static Result rehabilitarEmpleado(Long id) {

        Empleado empleado = (Empleado) repositorioPersona.buscarPorId(id);
        empleado.setCesadoFalse();
        repositorioPersona.modificar(empleado);

        return ok();
    }

}
