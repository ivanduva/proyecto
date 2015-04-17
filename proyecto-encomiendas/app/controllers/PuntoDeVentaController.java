package controllers;

import dao.PersistenciaDBPuntoDeVenta;
import model.PuntoDeVenta;
import play.*;
import play.data.Form;
import play.mvc.*;

import repository.PuntoDeVentaRepositorio;
import views.html.*;

/**
 * Created by Ivan on 13/04/2015.
 */
public class PuntoDeVentaController extends Controller{


    public static Result puntos() { return ok(pdv.render("Admin")); }

    public static Result agregarPunto() {
        PuntoDeVentaRepositorio repositorio = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());

        PuntoDeVenta punto = Form.form(PuntoDeVenta.class).bindFromRequest().get();
        repositorio.crear(punto);
        return redirect(routes.PuntoDeVentaController.puntos());
    }
}
