package model;

import javax.persistence.*;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
@Table (name = "localidad")
public class Localidad {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id_localidad")
    private Long localidadId;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "codigo_postal")
    private Long codigoPostal;

    @OneToOne
    private LatLong ubicacion;

    public Localidad(String nombre, Long codigoPostal) {
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
    }

    public Long getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(Long localidadId) {
        this.localidadId = localidadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Long codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public LatLong getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(LatLong ubicacion) {
        this.ubicacion = ubicacion;
    }
}
