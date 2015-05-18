package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dao.PersistenciaDBLocalidad;
import dao.PersistenciaDBPuntoDeVenta;
import model.Localidad;
import model.PuntoDeVenta;
import model.TipoPunto;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.LocalidadRepositorio;
import repository.PuntoDeVentaRepositorio;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by Ivan on 13/04/2015.
 */
public class PuntoDeVentaController extends Controller {

    static PuntoDeVentaRepositorio repositorioPdv = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());
    //static UsuarioRepositorio repositorioUsuario = new UsuarioRepositorio(new PersistenciaDBUsuario());
    static LocalidadRepositorio repositorioLocalidad = new LocalidadRepositorio(new PersistenciaDBLocalidad());

    public static Result agregarPunto() {

        try {
            JsonNode json = request().body().asJson();
            Logger.info(json.toString() + "\n");
            PuntoDeVenta puntoDeVenta = Json.fromJson(json, PuntoDeVenta.class);
            puntoDeVenta.getUsuario().setFechaCreacion(new Date());
            repositorioPdv.crear(puntoDeVenta);
            return ok(toJson(puntoDeVenta));
        } catch (PersistenceException e) {

            return badRequest(toJson("{status: 400, mensaje: 'Datos duplicados'}"));
        }
    }

    public static Result listarPuntos() {
        List<PuntoDeVenta> puntoDeVentas = repositorioPdv.listarTodo();
        return ok(toJson(puntoDeVentas));
    }

    public static Result getPunto(Long id) {

        try {
            Logger.info("GEEEEEEEEEEEEET\n");
            PuntoDeVenta puntoDeVenta = repositorioPdv.buscarPorId(id);

            //geLogger.info(toJson(puntoDeVenta).toString());
            return ok(toJson(puntoDeVenta));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result modificarPunto(Long id) {

        try {
            PuntoDeVenta puntoDeVenta = repositorioPdv.buscarPorId(id);
            JsonNode json = request().body().asJson();
            PuntoDeVenta puntoDeVentaJson = Json.fromJson(json, PuntoDeVenta.class);

            puntoDeVenta.setDireccion(puntoDeVentaJson.getDireccion());
            puntoDeVenta.setEmail(puntoDeVentaJson.getEmail());
            puntoDeVenta.setNombre(puntoDeVentaJson.getNombre());
            puntoDeVenta.setNombreResponsable(puntoDeVentaJson.getNombreResponsable());
            puntoDeVenta.setTipo(puntoDeVentaJson.getTipo());
            puntoDeVenta.setTelefono(puntoDeVentaJson.getTelefono());
            puntoDeVenta.setLocalidad(puntoDeVentaJson.getLocalidad());

            repositorioPdv.modificar(puntoDeVenta);
            return ok(toJson(puntoDeVenta));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result eliminarPunto(Long id) {

        try {
            Logger.info("ELIMINAAAAAAAAAAAAAAAAAAAR");
            PuntoDeVenta punto = repositorioPdv.buscarPorId(id);
            repositorioPdv.eliminar(punto);
            return ok(toJson(punto));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    // TODO: sacar a controlador de localidades
    public static Result listarLocalidades() {

        List<Localidad> localidades = repositorioLocalidad.listarTodo();
        return ok(toJson(localidades));
    }

    public static Result listarTipoPdv() {
        TipoPunto[] tiposPunto = TipoPunto.values();
        return ok(toJson(tiposPunto));
    }
}
