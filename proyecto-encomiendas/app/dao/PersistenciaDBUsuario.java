package dao;

import model.Usuario;
import play.db.ebean.Model;

import java.util.List;

/**
 * Created by Ivan on 18/04/2015.
 */
public class PersistenciaDBUsuario implements Persistencia<Usuario, Long> {

    public PersistenciaDBUsuario() {
    }

    @Override
    public void create(Usuario usuario) {
        usuario.save();
    }

    @Override
    public Usuario get(Long aLong) {
        return (Usuario) new Model.Finder(String.class, Usuario.class).byId(aLong);
    }

    @Override
    public void update(Usuario usuario) {
        usuario.update();
    }

    @Override
    public void delete(Usuario usuario) {
        usuario.delete();
    }

    @Override
    public List<Usuario> listAll() {
        return new Model.Finder(String.class, Usuario.class).all();
    }
}
