package controllers;

import dao.PersistenciaDB;
import dao.PersistenciaDBPuntoDeVenta;
import dao.PersistenciaDBUsuario;
import model.Localidad;
import model.PuntoDeVenta;
import model.TipoUsuario;
import model.Usuario;
import play.*;
import play.data.Form;
import play.mvc.*;

import repository.PuntoDeVentaRepositorio;
import repository.UsuarioRepositorio;
import views.html.*;

import java.util.List;

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
        return ok(toJson(punto));
    }

    public static Result listarPuntos() {
        List<PuntoDeVenta> lista = repositorioPdv.listarTodo();

        return ok(toJson(lista));
    }

    public static Result modificarPunto() {
        return ok();
    }
}
