package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Ivan on 15/02/2015.
 */
@Entity
@Table (name = "encomienda")
public class Encomienda {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id_encomienda")
    private Long encomiendaId;

    @Column (name = "destinatario")
    private String destinatario;

    @Column (name = "direccion_destino")
    private String direccionDestino;

    @Column (name = "fecha_entrega")
    private Date fechaEntrega;

    @Column (name = "estado")
    @Enumerated (EnumType.STRING)
    private EstadoEncomienda estado;

    @ManyToOne
    private Cliente remitente;

    @ManyToOne
    private Localidad localidad;

    @Column (name = "tarifa")
    private BigDecimal tarifa;

    public Encomienda(Long encomiendaId, String destinatario, String direccionDestino, Date fechaEntrega,
                      EstadoEncomienda estado, Cliente remitente, Localidad localidad, BigDecimal tarifa) {
        this.encomiendaId = encomiendaId;
        this.destinatario = destinatario;
        this.direccionDestino = direccionDestino;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.remitente = remitente;
        this.localidad = localidad;
        this.tarifa = tarifa;
    }

    public Long getEncomiendaId() {
        return encomiendaId;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public EstadoEncomienda getEstado() {
        return estado;
    }

    public Cliente getRemitente() {
        return remitente;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setEncomiendaId(Long encomiendaId) {
        this.encomiendaId = encomiendaId;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setEstadoEnCamino() {
        this.estado = EstadoEncomienda.EN_CAMINO;
    }

    public void setEstadoEnSucursal() {
        this.estado = EstadoEncomienda.EN_SUCURSAL;
    }

    public void setEstadoEntregada() {
        this.estado = EstadoEncomienda.ENTREGADA;
    }

    public void setEstadoRetrasada() {
        this.estado = EstadoEncomienda.RETRASADA;
    }

    public void setRemitente(Cliente remitente) {
        this.remitente = remitente;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }
}
