package repository;

import dao.Persistencia;
import model.Cliente;

/**
 * Created by Ivan on 05/04/2015.
 */
public class ClienteRepositorio extends Repositorio<Cliente, Long> {

    public ClienteRepositorio(Persistencia<Cliente, Long> dao) {
        super(dao);
    }
}
