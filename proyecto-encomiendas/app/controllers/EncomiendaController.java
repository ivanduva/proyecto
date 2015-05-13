package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dao.PersistenciaDBEncomienda;
import dao.PersistenciaDBEstadoEncomienda;
import dao.PersistenciaDBPuntoDeVenta;
import model.Encomienda;
import model.EstadoEncomienda;
import model.NombreEstadoEncomienda;
import model.PuntoDeVenta;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.EncomiendaRepositorio;
import repository.EstadoEncomiendaRepositorio;
import repository.PuntoDeVentaRepositorio;

import java.util.Date;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by Ivan on 12/05/2015.
 */
public class EncomiendaController extends Controller {

    static EncomiendaRepositorio repositorioEncomienda = new EncomiendaRepositorio(new PersistenciaDBEncomienda());
    static EstadoEncomiendaRepositorio repositorioEstado = new EstadoEncomiendaRepositorio(new PersistenciaDBEstadoEncomienda());
    static PuntoDeVentaRepositorio repositorioPunto = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());

    public static Result registrarEncomienda (Long punto) {

        JsonNode json = request().body().asJson();
        Encomienda encomienda = Json.fromJson(json, Encomienda.class);
        PuntoDeVenta puntoDeVenta = repositorioPunto.buscarPorId(punto);
        EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date(), puntoDeVenta);
        estadoEncomienda.setNombreEnSucursal();
        estadoEncomienda.setEncomienda(encomienda);
        encomienda.agregarEstado(estadoEncomienda);

        repositorioEstado.crear(estadoEncomienda);
        repositorioEncomienda.crear(encomienda);

        return ok(toJson(encomienda));
    }

    public static Result getEncomienda (Long id) {

        Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
        Logger.info(toJson(encomienda).toString());
        return ok(toJson(encomienda));

    }

    public static Result despacharEncomienda (Long id) {

        Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
        EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date());
        estadoEncomienda.setNombreEnCamino();
        estadoEncomienda.setEncomienda(encomienda);

        repositorioEstado.crear(estadoEncomienda);
        encomienda.agregarEstado(estadoEncomienda);
        repositorioEncomienda.modificar(encomienda);

        return ok();
    }

    public static Result recibirEncomienda (Long id, Long punto) {

        Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
        EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date());
        estadoEncomienda.setNombreEnSucursal();
        estadoEncomienda.setEncomienda(encomienda);
        PuntoDeVenta puntoDeVenta = repositorioPunto.buscarPorId(punto);
        estadoEncomienda.setPuntoDeVenta(puntoDeVenta);

        repositorioEstado.crear(estadoEncomienda);
        encomienda.agregarEstado(estadoEncomienda);
        repositorioEncomienda.modificar(encomienda);
        return ok();

    }

   public static Result entregarEncomienda (Long id) {

       Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
       EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date());
       estadoEncomienda.setNombreEntregada();
       estadoEncomienda.setEncomienda(encomienda);

       repositorioEstado.crear(estadoEncomienda);
       encomienda.agregarEstado(estadoEncomienda);
       repositorioEncomienda.modificar(encomienda);

       return ok();
   }

    public static Result eliminarEncomienda (Long id) {

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
        List<EstadoEncomienda> historico = encomienda.getEstados();
        return ok(toJson(historico));
    }

}
