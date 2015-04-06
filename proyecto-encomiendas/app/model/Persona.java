package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
public class Persona {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id_persona")
    private Long personaId;

    @Column (name = "email", table = "cliente")
    private String email;

    @Column (name = "fecha_nacimiento", table = "cliente")
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn (name = "id_localidad")
    private Localidad localidad;

    @Column (name = "nombre", table = "cliente")
    private String nombre;

    @Column (name = "telefono", table = "cliente")
    private String telefono;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "id_usuario")
    private Usuario usuario;

    public Persona(Long personaId, String email, Date fechaNacimiento, Localidad localidad, String nombre,
                   String telefono) {
        this.personaId = personaId;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.localidad = localidad;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Persona(String email, Date fechaNacimiento, Localidad localidad, String nombre,
                   String telefono) {
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.localidad = localidad;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
