package security;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 01/05/2015.
 */
//Sirve para instanciar handlers de MyDynamicResourceHandler
public class AbstractDynamicResourceHandler implements DynamicResourceHandler {

    private static final Map<String, DynamicResourceHandler> HANDLERS = new HashMap<String, DynamicResourceHandler>();

    @Override
    public boolean isAllowed(String s, String s1, DeadboltHandler deadboltHandler, Http.Context context) {
        return false;
    }

    @Override
    public boolean checkPermission(String s, DeadboltHandler deadboltHandler, Http.Context context) {
        return false;
    }
}
