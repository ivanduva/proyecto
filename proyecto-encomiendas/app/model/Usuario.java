package model;

import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Created by Ivan on 05/04/2015.
 */
@Entity
@Table (name = "usuario")
public class Usuario extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long usuarioId;

    @Column (name = "nombre_usuario")
    private String nombreUsuario;

    @Column (name = "tipo_usuario")
    @Enumerated (EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column (name = "contrasena")
    private String contraseña;

    public Usuario(Long usuarioId, String nombreUsuario, TipoUsuario tipo, String contraseña) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.tipoUsuario = tipo;
        this.contraseña = contraseña;
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

    public TipoUsuario getTipo() {
        return tipoUsuario;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipoUsuario = tipo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
