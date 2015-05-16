package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ivan on 05/04/2015.
 */
@Entity
@Table (name = "servicio")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id_servicio")
    private Long servicioId;

    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "habilitado")
    private boolean habilitado;

    @OneToMany (cascade = CascadeType.ALL)
    private List<FranjaHoraria> franjasHorarias;

    public Servicio(Long servicioId, String descripcion, String nombre, boolean habilitado, List<FranjaHoraria> franjasHorarias) {
        this.servicioId = servicioId;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.habilitado = habilitado;
        this.franjasHorarias = franjasHorarias;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<FranjaHoraria> getFranjasHorarias() {
        return franjasHorarias;
    }

    public void setFranjasHorarias(List<FranjaHoraria> franjasHorarias) {
        this.franjasHorarias = franjasHorarias;
    }
}
