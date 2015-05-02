package repository;

import dao.PersistenciaDB;
import dao.PersistenciaDBUsuario;
import model.Usuario;

/**
 * Created by Ivan on 05/04/2015.
 */
public class UsuarioRepositorio extends Repositorio<Usuario, Long> {

    PersistenciaDB<Usuario, Long> dao;


    public UsuarioRepositorio(PersistenciaDBUsuario dao) {
        super(dao);
    }


    public Usuario buscarPorToken (String s){
        return dao.findByAuthToken(s);
    }

}
