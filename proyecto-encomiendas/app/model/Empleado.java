package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ivan on 12/05/2015.
 */
@Entity
//@Table (name = "empleado")
@DiscriminatorValue(value = "E")
//@PrimaryKeyJoinColumn(name = "legajo", referencedColumnName = "id_persona")
public class Empleado extends Persona {

    @Column(name = "cuit", unique=true)
    private String cuit;

    @Column(name = "dni", unique=true)
    private Long dni;

    @Column(name = "horario")
    private String horario;

    @Column(name = "cesado")
    private boolean cesado;


    public Empleado(String email, Date fechaNacimiento, Localidad localidad, String nombre, String telefono,
                    String cuit, Long dni, String horario) {
        super(email, fechaNacimiento, localidad, nombre, telefono);
        this.cuit = cuit;
        this.dni = dni;
        this.horario = horario;
        this.cesado = false;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean isCesado () {
        return cesado;
    }

    public void setCesadoTrue() {
        this.cesado = true;
    }

    public void setCesadoFalse() {
        this.cesado = false;
    }

    public void setCesado(boolean cesado) { this.cesado = cesado; }
}
