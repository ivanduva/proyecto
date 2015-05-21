package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dao.PersistenciaDBLocalidad;
import dao.PersistenciaDBPuntoDeVenta;
import model.Localidad;
import model.PuntoDeVenta;
import model.TipoPunto;
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

    private static PuntoDeVentaRepositorio repositorioPdv = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());
    private static LocalidadRepositorio repositorioLocalidad = new LocalidadRepositorio(new PersistenciaDBLocalidad());

    public static Result agregarPunto() {


        JsonNode json = request().body().asJson();
        PuntoDeVenta puntoDeVenta = Json.fromJson(json, PuntoDeVenta.class);
        puntoDeVenta.getUsuario().setFechaCreacion(new Date());

        try {
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



        PuntoDeVenta puntoDeVenta = repositorioPdv.buscarPorId(id);
        if (puntoDeVenta != null) {
            return ok(toJson(puntoDeVenta));
        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result modificarPunto(Long id) {


        PuntoDeVenta puntoDeVenta = repositorioPdv.buscarPorId(id);
        if (puntoDeVenta != null) {
            JsonNode json = request().body().asJson();

            PuntoDeVenta puntoDeVentaJson = Json.fromJson(json, PuntoDeVenta.class);

            puntoDeVenta.setNombre(puntoDeVentaJson.getNombre());
            puntoDeVenta.setDireccion(puntoDeVentaJson.getDireccion());
            puntoDeVenta.setEmail(puntoDeVentaJson.getEmail());
            puntoDeVenta.setLocalidad(puntoDeVentaJson.getLocalidad());
            puntoDeVenta.setNombreResponsable(puntoDeVentaJson.getNombreResponsable());
            puntoDeVenta.setTelefono(puntoDeVentaJson.getTelefono());
            puntoDeVenta.setTipo(puntoDeVentaJson.getTipo());
            //puntoDeVenta.setUsuario(puntoDeVentaJson.getUsuario());

            try {
                repositorioPdv.modificar(puntoDeVenta);
                return ok(toJson(puntoDeVenta));
            } catch (PersistenceException e) {
                return badRequest(toJson("{status: 400, mensaje: 'Datos duplicados'}"));
            }

        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result eliminarPunto(Long id) {


        PuntoDeVenta punto = repositorioPdv.buscarPorId(id);

        if (punto != null) {
            repositorioPdv.eliminar(punto);
            return ok(toJson(punto));
        } else {
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
