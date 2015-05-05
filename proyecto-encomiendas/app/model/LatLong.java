package model;


import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
@Table(name = "lat_long")
public class LatLong {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id_lat_long")
    private Long latLongId;

    @Column (name = "latitud")
    @Type(type = "org.hibernatespatial.GeometryUserType")
    private float latitud;

    @Column (name = "latitud")
    private float longitud;

    public LatLong(Long latLongId, float latitud, float longitud) {
        this.latLongId = latLongId;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getLatLongId() {
        return latLongId;
    }

    public float getLatitud() {
        return latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLatLongId(Long latLongId) {
        this.latLongId = latLongId;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
