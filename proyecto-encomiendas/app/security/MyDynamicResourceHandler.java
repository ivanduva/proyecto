package security;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import play.Logger;
import play.libs.F;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ivan on 02/05/2015.
 */
public class MyDynamicResourceHandler implements DynamicResourceHandler {

    private static final Map<String, DynamicResourceHandler> HANDLERS = new HashMap<String, DynamicResourceHandler>();

    @Override
    public boolean isAllowed(String name, String meta, DeadboltHandler deadboltHandler, Http.Context context) {

        DynamicResourceHandler handler = HANDLERS.get(name);
        boolean result = false;
        if (handler == null)
        {
            Logger.error("No handler available for " + name);
        }
        else
        {
            result = handler.isAllowed(name,
                    meta,
                    deadboltHandler,
                    context);
        }
        return result;
    }

    @Override
    public boolean checkPermission(final String permissionValue, final DeadboltHandler deadboltHandler, final Http.Context ctx) {
        return deadboltHandler.getSubject(ctx)
                .map(new F.Function<Subject, Boolean>()
                {
                    @Override
                    public Boolean apply(Subject subject) throws Throwable
                    {
                        boolean permissionOk = false;

                        if (subject != null)
                        {
                            List<? extends Permission> permissions = subject.getPermissions();
                            for (Iterator<? extends Permission> iterator = permissions.iterator(); !permissionOk && iterator.hasNext(); )
                            {
                                Permission permission = iterator.next();
                                permissionOk = permission.getValue().contains(permissionValue);
                            }
                        }

                        return permissionOk;
                    }
                }).get(1, TimeUnit.SECONDS);
    }
}
