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

    public static Result crearVenta() {

        Long monto = (long) 0;
        JsonNode json = request().body().asJson();
        Venta venta = Json.fromJson(json, Venta.class);
        venta.setFecha(new Date());
        //venta.getCliente().aumentarPuntosViajero(10);
        for (int i=0; i<venta.getEncomiendas().size(); i++) {
            EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date(), venta.getPuntoDeVenta());
            estadoEncomienda.setNombreEnSucursal();
            venta.getEncomiendas().get(i).agregarEstado(estadoEncomienda);
            venta.getEncomiendas().get(i).setRemitente(venta.getCliente());
            monto += venta.getEncomiendas().get(i).getTarifa();
        }

        venta.setValorFinal(monto);

        repositorioVenta.crear(venta);

        return ok(toJson(venta));
    }

    public static Result getVenta(Long id) {


        try {
            Venta venta = repositorioVenta.buscarPorId(id);
            return ok(toJson(venta));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }

    }

    //public static Result registrarEncomienda (Long id) {

        //JsonNode json = request().body().asJson();
        //Encomienda encomienda = Json.fromJson(json, Encomienda.class);

        //try {
        //    Venta venta = repositorioVenta.buscarPorId(id);
      //      EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date(), venta.getPuntoDeVenta());
    //        estadoEncomienda.setNombreEnSucursal();
  //          encomienda.agregarEstado(estadoEncomienda);
//
    //        if (venta.getCliente() != null) {
  //              encomienda.setRemitente(venta.getCliente());
//            }

            //venta.setFinalizadaFalse();

            //venta.agregarEncomienda(encomienda);
            //repositorioVenta.modificar(venta);

          //  return ok(toJson(encomienda));

        //} catch (NullPointerException e) {
        //    return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
      //  }


    //}

    public static Result getEncomienda (Long id) {


        try {
            Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
            Logger.info(toJson(encomienda).toString());
            return ok(toJson(encomienda));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }



    }

    public static Result despacharEncomienda (Long id) {

        try {
            Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
            EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date());
            estadoEncomienda.setNombreEnCamino();

            encomienda.agregarEstado(estadoEncomienda);
            repositorioEncomienda.modificar(encomienda);

            return ok(toJson(encomienda));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }


    }

    public static Result recibirEncomienda (Long id, Long punto) {

        try {
            Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
            EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date());
            estadoEncomienda.setNombreEnSucursal();
            PuntoDeVenta puntoDeVenta = repositorioPunto.buscarPorId(punto);
            estadoEncomienda.setPuntoDeVenta(puntoDeVenta);

            encomienda.agregarEstado(estadoEncomienda);
            repositorioEncomienda.modificar(encomienda);
            return ok(toJson(encomienda));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }

    }

   public static Result entregarEncomienda (Long id) {

       try {
           Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
           EstadoEncomienda estadoEncomienda = new EstadoEncomienda(new Date());
           estadoEncomienda.setNombreEntregada();


           encomienda.agregarEstado(estadoEncomienda);
           repositorioEncomienda.modificar(encomienda);

           return ok(toJson(encomienda));

       } catch (NullPointerException e) {
           return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
       }
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

        try {
            Encomienda encomienda = repositorioEncomienda.buscarPorId(id);
            List<EstadoEncomienda> historico = encomienda.getEstados();
            return ok(toJson(historico));

        } catch (NullPointerException e) {
            return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
        }
    }

    //public static Result finalizarVenta(Long id) {

        //try {
            //Long monto = (long) 0;
            //Venta venta = repositorioVenta.buscarPorId(id);
            //venta.setFecha(new Date());

            //for (int i = 0; i < venta.getEncomiendas().size(); i++) {
              //  monto += venta.getEncomiendas().get(i).getTarifa();
            //}
            //venta.setValorFinal(monto);
            //venta.setFinalizadaTrue();

            //repositorioVenta.modificar(venta);

          //  return ok(toJson(venta));

        //} catch (NullPointerException e) {
      //      return notFound(toJson("{status: 404, mensaje: 'Entidad no encontrada'}"));
    //    }
  //  }

    public static Result generarOrden(Long id) {

        List<Venta> ventas = repositorioVenta.listarTodo();
        List<Encomienda> encomiendas = new ArrayList<Encomienda>();
        int k = 0;

        for (int i = 0; i<ventas.size(); i++) {
            if (ventas.get(i).getPuntoDeVenta().getPuntoId() == id) {
                for (int j = 0; j<ventas.get(i).getEncomiendas().size(); j++) {
                    for (k = 0; k<ventas.get(i).getEncomiendas().get(j).getEstados().size()-1; k++);
                        if ((ventas.get(i).getEncomiendas().get(j).getEstados().get(k).getNombre()
                                == NombreEstadoEncomienda.EN_SUCURSAL) &&
                                (ventas.get(i).getEncomiendas().get(j).getEstados().get(k).getPuntoDeVenta().getPuntoId() == id)) {
                            encomiendas.add(ventas.get(i).getEncomiendas().get(j));
                        }
                }
            }
        }

        return ok(toJson(encomiendas));
    }

}
