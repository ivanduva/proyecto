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

}
