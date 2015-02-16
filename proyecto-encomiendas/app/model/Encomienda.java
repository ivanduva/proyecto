package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ivan on 15/02/2015.
 */
@Entity
public class Encomienda {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
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
    @JoinColumn (name = "cliente_id")
    private Cliente remitente;

    @ManyToOne
    @JoinColumn (name = "localidad_id")
    private Localidad localidad;

    public Encomienda(Long encomiendaId, String destinatario, String direccionDestino, Date fechaEntrega, EstadoEncomienda estado, Cliente remitente, Localidad localidad) {
        this.encomiendaId = encomiendaId;
        this.destinatario = destinatario;
        this.direccionDestino = direccionDestino;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.remitente = remitente;
        this.localidad = localidad;
    }
}
