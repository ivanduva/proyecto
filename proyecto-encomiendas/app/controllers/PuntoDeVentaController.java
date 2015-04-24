package controllers;

import dao.PersistenciaDBPuntoDeVenta;
import dao.PersistenciaDBUsuario;
import model.PuntoDeVenta;
import model.TipoUsuario;
import model.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repository.PuntoDeVentaRepositorio;
import repository.UsuarioRepositorio;


import java.util.List;
import views.html.pdv;

import static play.libs.Json.toJson;

/**
 * Created by Ivan on 13/04/2015.
 */
public class PuntoDeVentaController extends Controller {

    static PuntoDeVentaRepositorio repositorioPdv = new PuntoDeVentaRepositorio(new PersistenciaDBPuntoDeVenta());
    static UsuarioRepositorio repositorioUsuario = new UsuarioRepositorio(new PersistenciaDBUsuario());

    public static Result agregarPunto() {

        PuntoDeVenta punto = Form.form(PuntoDeVenta.class).bindFromRequest().get();
        Usuario usuario = Form.form(Usuario.class).bindFromRequest().get();
        usuario.setTipo(TipoUsuario.VENDEDOR);
        repositorioUsuario.crear(usuario);
        punto.setUsuario(usuario);
        repositorioPdv.crear(punto);
        return listarPuntos();
    }

    public static Result listarPuntos() {
        List<PuntoDeVenta> puntoDeVentas = repositorioPdv.listarTodo();
        return ok(pdv.render(puntoDeVentas));
    }

    public static Result modificarPunto() {
        return ok();
    }
}
