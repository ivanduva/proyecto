package model;

import org.postgresql.geometric.PGpoint;

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
    private PGpoint latitud;

    @Column (name = "latitud")
    private PGpoint longitud;

    public LatLong(Long latLongId, PGpoint latitud, PGpoint longitud) {
        this.latLongId = latLongId;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getLatLongId() {
        return latLongId;
    }

    public PGpoint getLatitud() {
        return latitud;
    }

    public PGpoint getLongitud() {
        return longitud;
    }

    public void setLatLongId(Long latLongId) {
        this.latLongId = latLongId;
    }

    public void setLatitud(PGpoint latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(PGpoint longitud) {
        this.longitud = longitud;
    }
}
