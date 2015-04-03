package repository;

import dao.Persistencia;
import model.Encomienda;
import java.util.Date;

/**
 * Created by Ivan on 02/04/2015.
 */
public class EncomiendaRepositorio extends Repositorio<Encomienda, Long> {
    public EncomiendaRepositorio(Persistencia dao) {
        super(dao);
    }

    public void registrarEncomienda(Encomienda encomienda){


    }

    public void despacharEncomienda (Long codigoBarra){

        Encomienda encomienda;
        encomienda = buscarPoId(codigoBarra);
        encomienda.setEstadoEnCamino();
        modificar(encomienda);

    }

    public void entregarEncomienda (Long codigoBarra){

        Encomienda encomienda;
        encomienda = buscarPoId(codigoBarra);
        encomienda.setEstadoEntregada();
        modificar(encomienda);

    }

    public void recibirEncomienda (Long codigoBarra){

        Encomienda encomienda;
        encomienda = buscarPoId(codigoBarra);
        encomienda.setEstadoEnSucursal();
        modificar(encomienda);

    }
}
