package controllers;

import dao.PersistenciaDBUsuario;
import model.Usuario;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repository.UsuarioRepositorio;

/**
 * Created by Ivan on 02/05/2015.
 */
public class Secured extends Security.Authenticator {

    static UsuarioRepositorio repositorio = new UsuarioRepositorio(new PersistenciaDBUsuario());

    @Override
    public String getUsername(Http.Context context) {
        Usuario user = null;

        String[] authTokenHeaderValues = context.request().headers().get(SecurityController.AUTH_TOKEN_HEADER);
        if ((authTokenHeaderValues != null)
                && (authTokenHeaderValues.length == 1)
                && (authTokenHeaderValues[0] != null)) {
            user = repositorio.buscarPorToken(authTokenHeaderValues[0]);
            if (user != null) {
                context.args.put("usuario", user);
                return user.getNombreUsuario();
            }
        }

        return null;
    }

    //Este método redirecciona a la pantalla del login si el usuario ingresa mal
    @Override
    public Result onUnauthorized(Http.Context context) {

        //ACÁ HAY QUE PONER UN REDIRECT() AL LOGIN
        return ok();
    }
}
