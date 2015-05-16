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

//        PuntoDeVenta punto = Json.fromJson(request().body().asJson());
//        Usuario usuario = Form.form(Usuario.class).bindFromRequest().get();
//        usuario.setTipo(TipoUsuario.VENDEDOR);
//        repositorioUsuario.crear(usuario);
//        punto.setUsuario(usuario);
//        repositorioPdv.crear(punto);

        JsonNode json = request().body().asJson();
        Logger.info(json.toString() + "\n");
        PuntoDeVenta puntoDeVenta = Json.fromJson(json, PuntoDeVenta.class);
        puntoDeVenta.getUsuario().setFechaCreacion(new Date());
        repositorioPdv.crear(puntoDeVenta);
        return ok(toJson(puntoDeVenta));
    }

    public static Result listarPuntos() {
        List<PuntoDeVenta> puntoDeVentas = repositorioPdv.listarTodo();
        return ok(toJson(puntoDeVentas));
    }

    public static Result getPunto(Long id) {
        Logger.info("GEEEEEEEEEEEEET\n");
        PuntoDeVenta puntoDeVenta = repositorioPdv.buscarPorId(id);
        Logger.info(toJson(puntoDeVenta).toString());
        return ok(toJson(puntoDeVenta));
    }

    public static Result modificarPunto(Long id) {
        JsonNode json = request().body().asJson();
        PuntoDeVenta puntoDeVenta = Json.fromJson(json, PuntoDeVenta.class);
        repositorioPdv.modificar(puntoDeVenta);
        return ok();
    }

    public static Result eliminarPunto(Long id) {
        Logger.info("ELIMINAAAAAAAAAAAAAAAAAAAR");
        PuntoDeVenta punto = repositorioPdv.buscarPorId(id);
        repositorioPdv.eliminar(punto);
        return ok();
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
