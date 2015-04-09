package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
@Table (name = "cliente")
@DiscriminatorValue("1")
public class Cliente extends Persona {

    @Column (name = "categoria")
    private String categoria;

    @Column (name = "puede_reservar")
    private boolean puedeReservar;

    @Column (name = "puntos_viajero")
    private int puntosViajero;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Venta> compras;

    public Cliente(Long personaId, String email, Date fechaNacimiento, Localidad localidad, String nombre, String telefono, String categoria, boolean puedeReservar, int puntosViajero, List<Venta> compras) {
        super(personaId, email, fechaNacimiento, localidad, nombre, telefono);
        this.categoria = categoria;
        this.puedeReservar = puedeReservar;
        this.puntosViajero = puntosViajero;
        this.compras = compras;
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
