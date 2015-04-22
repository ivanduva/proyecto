package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin;
import views.html.servicios;
import views.html.personas;
import views.html.pdv;

public class Application extends Controller {

    public static Result admin() {
        return ok(admin.render("Admin"));
    }

    public static Result servicios() {
        return ok(servicios.render("Admin"));
    }

    public static Result personas() {
        return ok(personas.render("Admin"));
    }

//    public static Result puntos() {
//        return ok(pdv.render("Admin"));
//    }

}
