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

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by Ivan on 12/05/2015.
 */
public class PersonaController extends Controller {

    //static UsuarioRepositorio repositorioUsuario = new UsuarioRepositorio(new PersistenciaDBUsuario());
    private static PersonaRepositorio repositorioPersona = new PersonaRepositorio(new PersistenciaDBPersona());
    private static SecurityRoleRepositorio repositorioRol = new SecurityRoleRepositorio(new PersistenciaDBSecurityRole());


    public static Result crearCliente() {


        JsonNode json = request().body().asJson();
        Cliente cliente = Json.fromJson(json, Cliente.class);

        cliente.setPuntosViajero(0);
        cliente.getUsuario().setFechaCreacion(new Date());
        cliente.getUsuario().agregarRol(repositorioRol.buscarPorId(1L));

        if (cliente.getPuedeReservar() == null) {
            cliente.setPuedeReservar("NO");
        }

        try {
            repositorioPersona.crear(cliente);
            return ok(toJson(cliente));
        } catch (PersistenceException e) {
            return badRequest(toJson("{status: 400, mensaje: 'Datos duplicados'}"));
        }
    }

    public static Result crearEmpleado() {
        JsonNode json = request().body().asJson();
        Empleado empleado = Json.fromJson(json, Empleado.class);
        empleado.getUsuario().setFechaCreacion(new Date());
        empleado.getUsuario().agregarRol(repositorioRol.buscarPorId((long) 4));
        empleado.setCesadoFalse();

        try {
            repositorioPersona.crear(empleado);
            return ok(toJson(empleado));
        } catch (PersistenceException e) {
            return badRequest(toJson("{status: 400, mensaje: 'Datos duplicados'}"));
        }
    }

    public static Result getPersona(Long id) {
        Persona persona = repositorioPersona.buscarPorId(id);

        if (persona != null) {
            return ok(toJson(persona));
        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result modificarCliente(Long id) {

        Cliente cliente = (Cliente) repositorioPersona.buscarPorId(id);
        if (cliente != null) {
            JsonNode json = request().body().asJson();
            Cliente clienteJson = Json.fromJson(json, Cliente.class);

            cliente.setCategoria(clienteJson.getCategoria());
            cliente.setPuedeReservar(clienteJson.getPuedeReservar());
            cliente.setPuntosViajero(clienteJson.getPuntosViajero());
            cliente.setEmail(clienteJson.getEmail());
            cliente.setFechaNacimiento(clienteJson.getFechaNacimiento());
            cliente.setLocalidad(clienteJson.getLocalidad());
            cliente.setNombre(clienteJson.getNombre());
            cliente.setTelefono(clienteJson.getTelefono());
            //cliente.setUsuario(clienteJson.getUsuario());


            try {
                repositorioPersona.modificar(cliente);
                return ok(toJson(clienteJson));
            } catch (PersistenceException e) {
                return badRequest(toJson("{status: 400, mensaje: 'Datos duplicados'}"));
            }

        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result modificarEmpleado(Long id) {


        Empleado empleado = (Empleado) repositorioPersona.buscarPorId(id);
        if (empleado != null) {
            JsonNode json = request().body().asJson();
            Empleado empleadoJson = Json.fromJson(json, Empleado.class);

            empleado.setCuit(empleadoJson.getCuit());
            empleado.setDni(empleadoJson.getDni());
            empleado.setHorario(empleadoJson.getHorario());
            empleado.setEmail(empleadoJson.getEmail());
            empleado.setFechaNacimiento(empleadoJson.getFechaNacimiento());
            empleado.setLocalidad(empleadoJson.getLocalidad());
            empleado.setNombre(empleadoJson.getNombre());
            empleado.setTelefono(empleadoJson.getTelefono());
            //empleado.setUsuario(empleadoJson.getUsuario());
            empleado.setCesado(empleadoJson.isCesado());

            try {
                repositorioPersona.modificar(empleado);
                return ok(toJson(empleadoJson));
            } catch (PersistenceException e) {
                return badRequest(toJson("{status: 400, mensaje: 'Datos duplicados'}"));
            }
        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result listarClientes() {

        //Ya se qué esto no es lo mejor, pero es una solución por ahora
        List<Persona> clientes = repositorioPersona.listarAlgunos("tipo", "C");
        return ok(toJson(clientes));
    }

    public static Result listarEmpleados() {

        //Ya se qué esto no es lo mejor, pero es una solución por ahora
        List<Persona> empleados = repositorioPersona.listarAlgunos("tipo", "E");
        List<Persona> empleadosNoCesados = new ArrayList<Persona>();

        for (Persona empleado: empleados) {
            if (! ((Empleado) empleado).isCesado()) {
                empleadosNoCesados.add(empleado);
            }
        }

        return ok(toJson(empleadosNoCesados));
    }

    public static Result eliminarCliente(Long id) {


        Cliente cliente = (Cliente) repositorioPersona.buscarPorId(id);
        if (cliente != null) {
            repositorioPersona.eliminar(cliente);
            return ok(toJson(cliente));
        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

//    public static Result cesarEmpleado(Long id) {
//        Empleado empleado = (Empleado) repositorioPersona.buscarPorId(id);
//        if (empleado != null) {
//            empleado.setCesadoTrue();
//            repositorioPersona.modificar(empleado);
//            return ok(toJson(empleado));
//        } else {
//            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
//        }
//    }
//
//    public static Result rehabilitarEmpleado(Long id) {
//        Empleado empleado = (Empleado) repositorioPersona.buscarPorId(id);
//        if (empleado != null) {
//            empleado.setCesadoFalse();
//            repositorioPersona.modificar(empleado);
//            return ok(toJson(empleado));
//        } else {
//            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
//        }
//    }

}
