package model;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
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

    @Constraints.MaxLength(256)
    @Constraints.Required
    @Constraints.Email
    @Column (name = "nombre_usuario", length = 256, unique = true, nullable = false)
    private String nombreUsuario;

    @Column (name = "fecha_creacion")
    private Date fechaCreacion;

    //@Column(length = 64, nullable = false)
    //private byte[] shaPassword;

    //@Transient
    @Constraints.Required
    @Constraints.MinLength(6)
    @Constraints.MaxLength(256)
    //@JsonIgnore
    private String password;

    @ManyToMany (cascade = CascadeType.ALL)
    public List<SecurityRole> roles;

    @ManyToMany
    public List<UserPermission> permisos;

    //token para verificar si el usuario está logueado
    private String authToken;

    @JsonIgnore
    private String identifier;

    public Usuario(String nombreUsuario, Date fechaCreacion) {

        this.nombreUsuario = nombreUsuario;
        this.fechaCreacion = fechaCreacion;
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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
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

    //public static byte[] getSha512(String value) {
      //  try {
        //    return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        //}
        //catch (NoSuchAlgorithmException e) {
          //  throw new RuntimeException(e);
        //}
        //catch (UnsupportedEncodingException e) {
          //  throw new RuntimeException(e);
        //}
    //}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        //shaPassword = getSha512(password);
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
