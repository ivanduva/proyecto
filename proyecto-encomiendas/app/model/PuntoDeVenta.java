package model;

import com.fasterxml.jackson.databind.JsonNode;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by Ivan on 06/04/2015.
 */
@Entity
@Table(name = "punto_de_venta")
public class PuntoDeVenta extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_punto_de_venta")
    private Long puntoId;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "email")
    private String email;

    @OneToOne
    private Localidad localidad;

    @Column(name = "nombre", unique=true)
    private String nombre;

    @Column(name = "nombre_responsable")
    private String nombreResponsable;

    @Column(name = "telefono")
    private String telefono;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    //@OneToMany(cascade = CascadeType.ALL)
    //private List<Venta> ventas;

    @Column(name = "tipo_punto")
    @Enumerated(EnumType.STRING)
    private TipoPunto tipoPunto;


    public PuntoDeVenta() {
    }

    public PuntoDeVenta(Long puntoId, String direccion, String email, Localidad localidad, String nombre,
                        String nombreResponsable, String telefono, Usuario usuario,
                        TipoPunto tipo) {
        this.puntoId = puntoId;
        this.direccion = direccion;
        this.email = email;
        this.localidad = localidad;
        this.nombre = nombre;
        this.nombreResponsable = nombreResponsable;
        this.telefono = telefono;
        this.usuario = usuario;
        //this.ventas = ventas;
        this.tipoPunto = tipo;
    }

    public PuntoDeVenta(String direccion, String email, Localidad localidad, String nombre, String nombreResponsable,
                        String telefono, Usuario usuario, TipoPunto tipoPunto) {
        this.direccion = direccion;
        this.email = email;
        this.localidad = localidad;
        this.nombre = nombre;
        this.nombreResponsable = nombreResponsable;
        this.telefono = telefono;
        this.usuario = usuario;
       // this.ventas = ventas;
        this.tipoPunto = tipoPunto;
    }

    public static PuntoDeVenta fromJson(JsonNode jsonNode) {
        PuntoDeVenta punto = Json.fromJson(jsonNode, PuntoDeVenta.class);
        return punto;
    }


    public Long getPuntoId() {
        return puntoId;
    }

    public void setPuntoId(Long puntoId) {
        this.puntoId = puntoId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //public List<Venta> getVentas() {
    //    return ventas;
    //}

    //public void setVentas(List<Venta> ventas) {
      //  this.ventas = ventas;
    //}

    public TipoPunto getTipo() {
        return tipoPunto;
    }

    public void setTipo(TipoPunto tipo) {
        this.tipoPunto = tipo;
    }
}
