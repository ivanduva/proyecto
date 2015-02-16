package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
public class Cliente extends Persona {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long clienteId;

    @Column (name = "categoria")
    private String categoria;

    @Column (name = "puede_reservar")
    private boolean puedeReservar;

    @Column (name = "puntos_viajero")
    private int puntosViajero;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Venta> compras;

}
