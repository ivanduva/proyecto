package dao;

import model.PuntoDeVenta;
import play.db.ebean.Model;
import java.util.List;

/**
 * Created by Ivan on 13/04/2015.
 */
public class PersistenciaDBPuntoDeVenta implements Persistencia<PuntoDeVenta, Long> {


    public PersistenciaDBPuntoDeVenta() {
    }

    @Override
    public void create(PuntoDeVenta puntoDeVenta) {
        puntoDeVenta.save();
    }

    @Override
    public PuntoDeVenta get(Long aLong) {
        PuntoDeVenta punto = (PuntoDeVenta) new Model.Finder(String.class, PuntoDeVenta.class).byId(aLong);
        return punto;
    }

    @Override
    public void update(PuntoDeVenta puntoDeVenta) {
        puntoDeVenta.update();

    }

    @Override
    public void delete(PuntoDeVenta puntoDeVenta) {
        puntoDeVenta.delete();
    }

    @Override
    public List<PuntoDeVenta> listAll() {
        List<PuntoDeVenta> list = new Model.Finder(String.class, PuntoDeVenta.class).all();
        return list;
    }
}
