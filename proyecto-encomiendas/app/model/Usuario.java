package model;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ivan on 05/04/2015.
 */
@Entity
@Table (name = "usuario")
public class Usuario extends Model implements Subject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long usuarioId;

    @Column (name = "nombre_usuario")
    private String nombreUsuario;

    @ManyToMany
    public List<SecurityRole> roles;

    @ManyToMany
    public List<UserPermission> permisos;


    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void agregarRol (SecurityRole rol){
        this.roles.add(rol);
    }

    public void agregarPermiso (UserPermission permiso){
        this.permisos.add(permiso);
    }

    @Override
    public List<? extends Role> getRoles() {
        return roles;
    }

    @Override
    public List<? extends Permission> getPermissions() {
        return permisos;
    }

    @Override
    public String getIdentifier() {
        return nombreUsuario;
    }

}
