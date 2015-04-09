package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
@Table (name = "cliente")
public class Cliente extends Persona {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id_cliente")
    private Long clienteId;

    @Column (name = "categoria")
    private String categoria;

    @Column (name = "puede_reservar")
    private boolean puedeReservar;

    @Column (name = "puntos_viajero")
    private int puntosViajero;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Venta> compras;

    public Cliente(Long clienteId, String email, Date fechaNacimiento, Localidad localidad, String nombre, String telefono, String categoria, boolean puedeReservar, int puntosViajero, List<Venta> compras) {
        super(email, fechaNacimiento, localidad, nombre, telefono);
        this.clienteId = clienteId;
        this.categoria = categoria;
        this.puedeReservar = puedeReservar;
        this.puntosViajero = puntosViajero;
        this.compras = compras;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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
