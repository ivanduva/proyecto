package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {



    public static Result admin() { return ok(admin.render("Admin")); }

    public static Result servicios() { return ok(servicios.render("Admin")); }

    public static Result personas() { return ok(personas.render("Admin")); }

    public static Result puntos() { return ok(pdv.render("Admin")); }

}
