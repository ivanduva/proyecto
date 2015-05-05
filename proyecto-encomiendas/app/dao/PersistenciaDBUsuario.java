package dao;

import com.avaje.ebean.Ebean;
import model.Usuario;
import play.db.ebean.Model;

/**
 * Created by Ivan on 18/04/2015.
 */
public class PersistenciaDBUsuario extends PersistenciaDB<Usuario, Long> {

    public static final Model.Finder<Long, Usuario> find = new Model.Finder<Long, Usuario>(Long.class,
            Usuario.class);

    public PersistenciaDBUsuario() {
        super(Usuario.class);
    }

    @Override
    public void create(Usuario usuario) {
        Ebean.save(usuario);
        Ebean.saveManyToManyAssociations(usuario, "roles");
        Ebean.saveManyToManyAssociations(usuario, "permisos");
    }

    @Override
    public Usuario findByName(String s) {

        return find.where()
                .eq("nombre_usuario",
                        s)
                .findUnique();
    }

    @Override
    public int findRowCount() {
        return find.findRowCount();
    }

    public Usuario findByAuthToken(String s){
        if (s == null){
            return null;
        }

        try {
            return find.where().eq("authToken", s).findUnique();
        }
        catch (Exception e){
            return null;
        }
    }

}
