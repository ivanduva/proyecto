package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dao.PersistenciaDBEncomienda;
import dao.PersistenciaDBPersona;
import dao.PersistenciaDBPuntoDeVenta;
import dao.PersistenciaDBVenta;
import model.*;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.EncomiendaRepositorio;
import repository.PersonaRepositorio;
import repository.PuntoDeVentaRepositorio;
import repository.VentaRepositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.libs.Json.fromJson;
import static play.libs.Json.toJson;


public class EncomiendaController extends Controller {

    static EncomiendaRepositorio repositorioEncomienda = new EncomiendaRepositorio(new PersistenciaDBEncomienda());
    static PuntoDeVentaRepositorio repositorioPunto = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());
    static VentaRepositorio repositorioVenta = new VentaRepositorio(new PersistenciaDBVenta());
    static PersonaRepositorio repositorioPersona = new PersonaRepositorio(new PersistenciaDBPersona());
    static PuntoDeVentaRepositorio repositorioPdv = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());

    public static Result crearVenta() {

        long monto = 0L;
        JsonNode json = request().body().asJson();
        Venta venta = Json.fromJson(json, Venta.class);
        venta.setFecha(new Date());
        for (Encomienda encomienda : venta.getEncomiendas()) {
            EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date(), venta.getPuntoDeVenta());
            estadoEncomienda.setNombreEnSucursal();
            encomienda.agregarEstado(estadoEncomienda);
            encomienda.setRemitente(venta.getCliente());
            monto += encomienda.getTarifa();
        }

        venta.setValorFinal(monto);
        repositorioVenta.crear(venta);

        return ok(toJson(venta));
    }

    public static Result getVentas() {
        List<Venta> ventas = repositorioVenta.listarTodo();
        return ok(toJson(ventas));
    }

    public static Result getVenta(Long id) {

        Venta venta = repositorioVenta.buscarPorId(id);
        if (venta != null) {
            return ok(toJson(venta));
        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }

    }

    public static Result getEncomienda(Long id) {

        Encomienda encomienda = repositorioEncomienda.buscarPorId(id);

        if (encomienda != null) {
            return ok(toJson(encomienda));
        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result modificarEncomienda(Long id) {
        Encomienda encomienda = repositorioEncomienda.buscarPorId(id);

        if (encomienda != null) {
            JsonNode json = request().body().asJson();
            JsonNode siguienteEstadoJson = json.get("siguienteEstado");
            Encomienda encomiendaJson = fromJson(json.get("encomienda"), Encomienda.class);

            if (!siguienteEstadoJson.isNull()) {
                EstadoEncomienda siguienteEstado = getNextEstado(siguienteEstadoJson);
                encomiendaJson.agregarEstado(siguienteEstado);
            }

            repositorioEncomienda.modificar(encomiendaJson);
            return ok(toJson(encomiendaJson));
        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    private static EstadoEncomienda getNextEstado(JsonNode siguienteEstado) {
        EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date());

        String estado = siguienteEstado.get("tipoCambio").asText();

        if (estado.trim().equals("despachar")) {
            estadoEncomienda.setNombreEnCamino();
        } else if (estado.equals("entregar")) {
            estadoEncomienda.setNombreEntregada();
        } else if (estado.equals("recibir")) {
            PuntoDeVenta puntoDeVenta = Json.fromJson(siguienteEstado.path("puntoDeVenta"), PuntoDeVenta.class);
            estadoEncomienda.setNombreEnSucursal();
            estadoEncomienda.setPuntoDeVenta(puntoDeVenta);
        }

        estadoEncomienda.setFecha(new Date());
        return estadoEncomienda;

    }

    public static Result eliminarEncomienda(Long id) {

        Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
        repositorioEncomienda.eliminar(encomienda);
        return ok();
    }

    public static Result listarEstados() {

        NombreEstadoEncomienda[] estados = NombreEstadoEncomienda.values();
        return ok(toJson(estados));
    }

    public static Result listarHistoricoEstados(Long id) {

        Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
        if (encomienda != null) {
            List<EstadoEncomienda> historico = encomienda.getEstados();
            return ok(toJson(historico));
        } else {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    public static Result generarOrden(Long puntoId) {
        List<Venta> ventas = repositorioVenta.listarTodo();
        List<Encomienda> encomiendas = new ArrayList<Encomienda>();

        for (Venta venta : ventas) {
            if (venta.getPuntoDeVenta().getPuntoId().equals(puntoId)) {
                for (Encomienda encomienda : venta.getEncomiendas()) {
                    if (encomienda.isInThisPdv(puntoId)) {
                        encomiendas.add(encomienda);
                    }
                }
            }
        }

        return ok(toJson(encomiendas));
    }

}
