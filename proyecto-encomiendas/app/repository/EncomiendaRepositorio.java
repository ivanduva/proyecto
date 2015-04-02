package repository;

import dao.Persistencia;
import model.Cliente;
import model.Encomienda;
import model.EstadoEncomienda;
import model.Localidad;

import java.util.Date;

/**
 * Created by Ivan on 02/04/2015.
 */
public class EncomiendaRepositorio extends Repositorio<Encomienda, Long> {
    public EncomiendaRepositorio(Persistencia dao) {
        super(dao);
    }

    public Long registrarEncomienda(Localidad loc, Date fechaEntrega, Cliente remitente, String destinatario,
                                    String direccionDestinatario){

    }

    public void despacharEncomienda (Long codigoBarra){

    }

    public void entregarEncomienda (Long codigoBarra){

    }

    public void recibirEncomienda (Long codigoBarra){

    }
}
