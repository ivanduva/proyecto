package model;

import javax.persistence.*;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity (name = "lat_long")
public class LatLong {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long latLongId;

    @Column (name = "latitud")
    private double latitud;

    @Column (name = "latitud")
    private double longitud;

    public LatLong(Long latLongId, double latitud, double longitud) {
        this.latLongId = latLongId;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getLatLongId() {
        return latLongId;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLatLongId(Long latLongId) {
        this.latLongId = latLongId;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
