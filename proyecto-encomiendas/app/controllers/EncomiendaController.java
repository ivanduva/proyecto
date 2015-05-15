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

import static play.libs.Json.toJson;

/**
 * Created by Ivan on 12/05/2015.
 */
public class EncomiendaController extends Controller {

    static EncomiendaRepositorio repositorioEncomienda = new EncomiendaRepositorio(new PersistenciaDBEncomienda());
    static PuntoDeVentaRepositorio repositorioPunto = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());
    static VentaRepositorio repositorioVenta = new VentaRepositorio(new PersistenciaDBVenta());
    static PersonaRepositorio repositorioPersona = new PersonaRepositorio(new PersistenciaDBPersona());
    static PuntoDeVentaRepositorio repositorioPdv = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());

    public static Result crearVenta(Long idCliente, Long idPdv) {

        JsonNode json = request().body().asJson();
        Venta venta = Json.fromJson(json, Venta.class);

        if (idCliente != 0) {
            Cliente cliente = (Cliente) repositorioPersona.buscarPorId(idCliente);
            venta.setCliente(cliente);
        }

        PuntoDeVenta puntoDeVenta = repositorioPdv.buscarPorId(idPdv);
        venta.setPuntoDeVenta(puntoDeVenta);

        repositorioVenta.crear(venta);

        return ok(toJson(venta));
    }

    public static Result registrarEncomienda (Long idVenta) {

        JsonNode json = request().body().asJson();
        Encomienda encomienda = Json.fromJson(json, Encomienda.class);
        Venta venta = repositorioVenta.buscarPorId(idVenta);
        EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date(), venta.getPuntoDeVenta());
        estadoEncomienda.setNombreEnSucursal();
        encomienda.agregarEstado(estadoEncomienda);

        if (venta.getCliente() != null) {
            encomienda.setRemitente(venta.getCliente());
        }

        venta.setFinalizadaFalse();

        venta.agregarEncomienda(encomienda);
        repositorioVenta.modificar(venta);

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

        encomienda.agregarEstado(estadoEncomienda);
        repositorioEncomienda.modificar(encomienda);

        return ok();
    }

    public static Result recibirEncomienda (Long id, Long punto) {

        Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
        EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date());
        estadoEncomienda.setNombreEnSucursal();
        PuntoDeVenta puntoDeVenta = repositorioPunto.buscarPorId(punto);
        estadoEncomienda.setPuntoDeVenta(puntoDeVenta);

        encomienda.agregarEstado(estadoEncomienda);
        repositorioEncomienda.modificar(encomienda);
        return ok();

    }

   public static Result entregarEncomienda (Long id) {

       Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
       EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date());
       estadoEncomienda.setNombreEntregada();


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

    public static Result finalizarVenta(Long id) {

        Long monto = (long) 0;
        Venta venta = repositorioVenta.buscarPorId(id);
        venta.setFecha(new Date());

        for (int i = 0; i<venta.getEncomiendas().size(); i++){
            monto += venta.getEncomiendas().get(i).getTarifa();
        }
        venta.setValorFinal(monto);
        venta.setFinalizadaTrue();

        repositorioVenta.modificar(venta);

        return ok(toJson(venta));
    }

    public static Result generarOrden(Long punto) {

        List<Venta> ventas = repositorioVenta.listarTodo();
        List<Encomienda> encomiendas = new ArrayList<Encomienda>();
        int k = 0;

        for (int i = 0; i<ventas.size(); i++) {
            if (ventas.get(i).getPuntoDeVenta().getPuntoId() == punto) {
                for (int j = 0; j<ventas.get(i).getEncomiendas().size(); j++) {
                    for (k = 0; k<ventas.get(i).getEncomiendas().get(j).getEstados().size()-1; k++);
                        if ((ventas.get(i).getEncomiendas().get(j).getEstados().get(k).getNombre()
                                == NombreEstadoEncomienda.EN_SUCURSAL) &&
                                (ventas.get(i).getEncomiendas().get(j).getEstados().get(k).getPuntoDeVenta().getPuntoId() == punto)) {
                            encomiendas.add(ventas.get(i).getEncomiendas().get(j));
                        }
                }
            }
        }

        return ok(toJson(encomiendas));
    }

}
