package model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
//@Table (name = "cliente")
@DiscriminatorValue(value = "C")
//@PrimaryKeyJoinColumn (name = "id_cliente", referencedColumnName = "id_persona")
public class Cliente extends Persona {

    @Column (name = "categoria")
    private String categoria;

    @Column (name = "puede_reservar")
    private String puedeReservar;

    @Column (name = "puntos_viajero")
    private int puntosViajero;

   // @OneToMany (cascade = CascadeType.ALL)
    //private List<Venta> compras;


    public Cliente(String email, Date fechaNacimiento, Localidad localidad, String nombre, String telefono,
                   String categoria, String puedeReservar, int puntosViajero) {

        super(email, fechaNacimiento, localidad, nombre, telefono);
        this.categoria = categoria;
        this.puedeReservar = puedeReservar;
        this.puntosViajero = puntosViajero;
    }

    public Cliente(Long personaId, String email, Date fechaNacimiento, Localidad localidad, String nombre,
                   String telefono, String categoria, String puedeReservar, int puntosViajero) {
        super(personaId, email, fechaNacimiento, localidad, nombre, telefono);
        this.categoria = categoria;
        this.puedeReservar = puedeReservar;
        this.puntosViajero = puntosViajero;
//        this.compras = compras;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isPuedeReservar() {
        if (puedeReservar == "SI") {
            return true;
        } else {
            return false;
        }
    }

    public int getPuntosViajero() {
        return puntosViajero;
    }

    //public List<Venta> getCompras() {
      //  return compras;
    //}

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPuntosViajero(int puntosViajero) {
        this.puntosViajero = puntosViajero;
    }

    public void setPuedeReservar(String puedeReservar) {
        this.puedeReservar = puedeReservar;
    }

    public String getPuedeReservar() { return puedeReservar; }

    public void aumentarPuntosViajero(int pts) { this.puntosViajero += pts; }

   // public void setCompras(List<Venta> compras) {
     //   this.compras = compras;
    //}
}
