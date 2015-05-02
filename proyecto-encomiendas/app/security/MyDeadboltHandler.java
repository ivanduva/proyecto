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


    public F.Promise<Result> beforeAuthCheck(Http.Context context) {

        // returning null means that everything is OK.  Return a real result if you want a redirect to a login page or
        // somewhere else
        return F.Promise.pure(null);
    }

    public F.Promise<Subject> getSubject(final Http.Context context) {

        return F.Promise.promise(new F.Function0<Subject>()
        {
            @Override
            public Subject apply() throws Throwable {
                return (Subject) context.current().args.get("usuario");
            }
        });

    }

    @Override
    public F.Promise<Result> onAuthFailure(Http.Context context, String content) {
        return super.onAuthFailure(context, content);
    }

    public DynamicResourceHandler getDynamicResourceHandler(Http.Context context) {

        return new MyDynamicResourceHandler();

    }
}
