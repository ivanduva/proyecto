package model;

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

    @OneToOne(cascade = CascadeType.ALL)
    private PuntoDeVenta puntoDeVenta;

    public EstadoEncomienda(Long estadoEncomiendaId, NombreEstadoEncomienda nombre, Date fecha, PuntoDeVenta puntoDeVenta) {
        EstadoEncomiendaId = estadoEncomiendaId;
        this.nombre = nombre;
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
}
