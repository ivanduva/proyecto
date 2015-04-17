package controllers;

import dao.PersistenciaDBPuntoDeVenta;
import model.PuntoDeVenta;
import play.*;
import play.data.Form;
import play.mvc.*;

import repository.PuntoDeVentaRepositorio;
import views.html.*;

import java.util.List;

/**
 * Created by Ivan on 13/04/2015.
 */
public class PuntoDeVentaController extends Controller{

    static PuntoDeVentaRepositorio repositorio = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());


   // public static Result puntos() { return ok(pdv.render("Admin")); }

    public static Result agregarPunto() {

        PuntoDeVenta punto = Form.form(PuntoDeVenta.class).bindFromRequest().get();
        repositorio.crear(punto);
        return redirect(routes.Application.puntos());
    }

    public static Result listarPuntos () {

        List<PuntoDeVenta> lista = repositorio.listarTodo();

        return ok();
    }
}
