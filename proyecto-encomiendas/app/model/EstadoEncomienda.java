package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
@Table(name = "estado_encomienda")
public class EstadoEncomienda extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id_estado_encomienda")
    private Long EstadoEncomiendaId;

    @Column(name = "nombre")
    @Enumerated(EnumType.STRING)
    private NombreEstadoEncomienda nombre;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    private PuntoDeVenta puntoDeVenta;

    @ManyToOne
    @JsonIgnore
    private Encomienda encomienda;


    public EstadoEncomienda (Date fecha) {
        this.fecha = fecha;
    }

    public EstadoEncomienda(Date fecha, PuntoDeVenta puntoDeVenta) {
        this.fecha = fecha;
        this.puntoDeVenta = puntoDeVenta;
    }

    public Long getEstadoEncomiendaId() {
        return EstadoEncomiendaId;
    }

    public void setEstadoEncomiendaId(Long estadoEncomiendaId) {
        EstadoEncomiendaId = estadoEncomiendaId;
    }

    public NombreEstadoEncomienda getNombre() {
        return nombre;
    }

    public void setNombreEnCamino() {
        this.nombre = NombreEstadoEncomienda.EN_CAMINO;
    }

    public void setNombreEnSucursal() {
        this.nombre = NombreEstadoEncomienda.EN_SUCURSAL;
    }

    public void setNombreEntregada() {
        this.nombre = NombreEstadoEncomienda.ENTREGADA;
    }

    public void setNombreRetrasada() {
        this.nombre = NombreEstadoEncomienda.RETRASADA;
    }

    public void setNombre(String s) { this.nombre = NombreEstadoEncomienda.valueOf(s);}

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PuntoDeVenta getPuntoDeVenta() {
        return puntoDeVenta;
    }

    public void setPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
        this.puntoDeVenta = puntoDeVenta;
    }

    public Encomienda getEncomienda() {
        return encomienda;
    }

    public void setEncomienda(Encomienda encomienda) {
        this.encomienda = encomienda;
    }
}
