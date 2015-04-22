package repository;

import dao.Persistencia;
import dao.PersistenciaDBUsuario;
import model.Usuario;

/**
 * Created by Ivan on 05/04/2015.
 */
public class UsuarioRepositorio extends Repositorio<Usuario, Long> {

    PersistenciaDBUsuario dao;

    public UsuarioRepositorio(Persistencia<Usuario, Long> dao) {
        super(dao);
    }
}
