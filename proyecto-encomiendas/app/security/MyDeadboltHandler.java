package security;

import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Created by Ivan on 01/05/2015.
 */
public class MyDeadboltHandler extends AbstractDeadboltHandler {


    //en este método hay que poner lo que se hace cuando las restricciones son comprobadas
    //creo que conviene que retorne null y listo
    public F.Promise<Result> beforeAuthCheck(Http.Context context) {

        // returning null means that everything is OK.  Return a real result if you want a redirect to a login page or
        // somewhere else
        return F.Promise.pure(null);
    }

    //obtiene el usuario que está logueado actualmente
    public F.Promise<Subject> getSubject(final Http.Context context) {

        return F.Promise.promise(new F.Function0<Subject>()
        {
            @Override
            public Subject apply() throws Throwable {
                return (Subject) context.current().args.get("usuario");
            }
        });

    }

    //en este método hay que poner lo que hacemos cuando fallan las restricciones
    @Override
    public F.Promise<Result> onAuthFailure(Http.Context context, String content) {
        return super.onAuthFailure(context, content);
    }

    //obtiene la instancia de nuestro "DynamicResourceHandler"
    //Puede que nunca lo usemos si no vamos a usar restricciones para permisos o restricciones dinámicas
    public DynamicResourceHandler getDynamicResourceHandler(Http.Context context) {

        return new MyDynamicResourceHandler();

    }
}
