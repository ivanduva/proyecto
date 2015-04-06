package model;

import javax.persistence.*;

/**
 * Created by Ivan on 05/04/2015.
 */
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long usuarioId;

    @Column (name = "nombre_usuario")
    private String nombreUsuario;

    @Column (name = "tipo")
    @Enumerated (EnumType.STRING)
    private TipoUsuario tipo;

    @Column (name = "contrasena")
    private String contraseña;

    public Usuario(Long usuarioId, String nombreUsuario, TipoUsuario tipo, String contraseña) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.tipo = tipo;
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
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
