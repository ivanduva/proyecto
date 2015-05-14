package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<EstadoEncomienda> estados;

    @ManyToOne
    //@JoinColumn (table = "encomienda", name = "id_cliente")
    private Cliente remitente;

    @ManyToOne
    private Localidad localidad;

    @Column (name = "tarifa")
    private Long tarifa;

    public Encomienda(Long encomiendaId, String destinatario, String direccionDestino, Date fechaEntrega,
                      List<EstadoEncomienda> estados, Cliente remitente, Localidad localidad, Long tarifa) {
        this.encomiendaId = encomiendaId;
        this.destinatario = destinatario;
        this.direccionDestino = direccionDestino;
        this.fechaEntrega = fechaEntrega;
        this.estados = estados;
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

    public List<EstadoEncomienda> getEstados() {
        return estados;
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

    public void setEstado(List<EstadoEncomienda> estados) {
        this.estados = estados;
    }

    public void setRemitente(Cliente remitente) {
        this.remitente = remitente;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Long getTarifa() {
        return tarifa;
    }

    public void setTarifa(Long tarifa) {
        this.tarifa = tarifa;
    }

    public void agregarEstado(EstadoEncomienda estado) { this.estados.add(estado); }
}
