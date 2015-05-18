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
    static PersonaRepositorio repositorioPersona = new PersonaRepositorio(new PersistenciaDBPersona());
    static SecurityRoleRepositorio repositorioRol = new SecurityRoleRepositorio(new PersistenciaDBSecurityRole());

    //Lo usamos para cuando se registra el cliente por su cuenta y cuando lo registra el ADMIN o el VENDEDOR. Cuando lo
    //registra el ADMIN o el VENDEDOR, puede elegir si "Puede reservar". Si se registra el cliente por su cuenta, no.
    public static Result crearCliente() {

        try {
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
        } catch (PersistenceException e) {

            return badRequest(toJson("{status: 400, mensaje: 'Datos duplicados'}"));
        }
    }

    public static Result crearEmpleado() {

        try {
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
        } catch (PersistenceException e) {

            return badRequest(toJson("{status: 400, mensaje: 'Datos duplicados'}"));
        }
    }

    public static Result getPersona(Long id) {

        try {
            Persona persona = repositorioPersona.buscarPorId(id);

            return ok(toJson(persona));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result modificarCliente(Long id) {

        try {
            Cliente cliente = (Cliente) repositorioPersona.buscarPorId(id);
            JsonNode json = request().body().asJson();
            Cliente clienteJson = Json.fromJson(json, Cliente.class);

            cliente.setEmail(clienteJson.getEmail());
            cliente.setFechaNacimiento(clienteJson.getFechaNacimiento());
            cliente.setLocalidad(clienteJson.getLocalidad());
            cliente.setNombre(clienteJson.getNombre());
            cliente.setTelefono(clienteJson.getTelefono());
            cliente.setCategoria(clienteJson.getCategoria());
            cliente.setPuedeReservar(clienteJson.getPuedeReservar());
            cliente.setPuntosViajero(clienteJson.getPuntosViajero());
            cliente.setUsuario(clienteJson.getUsuario());

            repositorioPersona.modificar(cliente);

            return ok(toJson(cliente));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result modificarEmpleado(Long id) {

        try {
            Empleado empleado = (Empleado) repositorioPersona.buscarPorId(id);
            JsonNode json = request().body().asJson();
            Empleado empleadoJson = Json.fromJson(json, Empleado.class);

            empleado.setEmail(empleadoJson.getEmail());
            empleado.setFechaNacimiento(empleadoJson.getFechaNacimiento());
            empleado.setLocalidad(empleadoJson.getLocalidad());
            empleado.setNombre(empleadoJson.getNombre());
            empleado.setTelefono(empleadoJson.getTelefono());
            empleado.setCuit(empleadoJson.getCuit());
            empleado.setDni(empleadoJson.getDni());
            empleado.setHorario(empleadoJson.getHorario());
            empleado.setUsuario(empleado.getUsuario());

            repositorioPersona.modificar(empleado);

            return ok(toJson(empleado));

        } catch (NullPointerException e) {
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

        try {
            Cliente cliente = (Cliente) repositorioPersona.buscarPorId(id);
            repositorioPersona.eliminar(cliente);
            return ok(toJson(cliente));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result cesarEmpleado(Long id) {

        try {
            Empleado empleado = (Empleado) repositorioPersona.buscarPorId(id);
            empleado.setCesadoTrue();
            repositorioPersona.modificar(empleado);

            return ok(toJson(empleado));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result rehabilitarEmpleado(Long id) {

        try {
            Empleado empleado = (Empleado) repositorioPersona.buscarPorId(id);
            empleado.setCesadoFalse();
            repositorioPersona.modificar(empleado);

            return ok(toJson(empleado));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

}
