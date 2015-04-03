package model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Ivan on 16/02/2015.
 */
public class Persona {

    private Long personaId;

    @Column (name = "email", table = "cliente")
    private String email;

    @Column (name = "fecha_nacimiento", table = "cliente")
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn (name = "localidad_id", table = "cliente")
    private Localidad localidad;

    @Column (name = "nombre", table = "cliente")
    private String nombre;

    @Column (name = "telefono", table = "cliente")
    private String telefono;

    public Persona() {
        this.personaId = personaId;
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
