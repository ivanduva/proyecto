package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.PersistenciaDBUsuario;
import model.Usuario;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repository.UsuarioRepositorio;

import java.util.UUID;

/**
 * Created by Ivan on 02/05/2015.
 */
public class SecurityController extends Controller{

    public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String AUTH_TOKEN = "authToken";
    static UsuarioRepositorio repositorio = new UsuarioRepositorio(new PersistenciaDBUsuario());

    public static Usuario getUser() { return (Usuario) Http.Context.current().args.get("usuario");};

    public static Result login(String nombreUsuario, String contraseña){
//        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
//
  //      if (loginForm.hasErrors()) {
    //        return badRequest(loginForm.errorsAsJson());
      //  }

        //Login login = loginForm.get();

        Usuario user = repositorio.buscarPorNombre(nombreUsuario);

        if ((user == null) || (user.getPassword() != contraseña)) {
            return unauthorized();
        }
        else {
            user.setAuthToken(UUID.randomUUID().toString());
            repositorio.modificar(user);
            ObjectNode authTokenJson = Json.newObject();
            authTokenJson.put(AUTH_TOKEN, user.getAuthToken());
            response().setCookie(AUTH_TOKEN, user.getAuthToken());
            return ok(authTokenJson);
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result logout(){
        response().discardCookie(AUTH_TOKEN);
        Usuario user = getUser();
        user.setAuthToken(null);
        repositorio.modificar(user);
        return redirect("/");
    }

    public static class Login {

        @Constraints.Required
        @Constraints.Email
        public String emailAddress;

        @Constraints.Required
        public String password;
    }

}
