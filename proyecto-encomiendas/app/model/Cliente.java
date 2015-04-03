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

    public Cliente(Long clienteId, String categoria, boolean puedeReservar, int puntosViajero) {
        super();
        this.clienteId = clienteId;
        this.categoria = categoria;
        this.puedeReservar = puedeReservar;
        this.puntosViajero = puntosViajero;
    }



    public Long getClienteId() {
        return clienteId;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isPuedeReservar() {
        return puedeReservar;
    }

    public int getPuntosViajero() {
        return puntosViajero;
    }

    public List<Venta> getCompras() {
        return compras;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPuntosViajero(int puntosViajero) {
        this.puntosViajero = puntosViajero;
    }

    public void setPuedeReservar(boolean puedeReservar) {
        this.puedeReservar = puedeReservar;
    }

    public void setCompras(List<Venta> compras) {
        this.compras = compras;
    }
}
