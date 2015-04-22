package repository;

import dao.Persistencia;
import model.Venta;

/**
 * Created by Ivan on 05/04/2015.
 */
public class VentaRepositorio extends Repositorio<Venta, Long> {

    public VentaRepositorio(Persistencia<Venta, Long> dao) {
        super(dao);
    }
}
