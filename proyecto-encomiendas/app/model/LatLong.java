package model;


import org.hibernate.annotations.Type;
import org.postgis.Point;

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
    private Point latitud;

    @Column (name = "latitud")
    private Point longitud;

    public LatLong(Long latLongId, Point latitud, Point longitud) {
        this.latLongId = latLongId;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getLatLongId() {
        return latLongId;
    }

    public Point getLatitud() {
        return latitud;
    }

    public Point getLongitud() {
        return longitud;
    }

    public void setLatLongId(Long latLongId) {
        this.latLongId = latLongId;
    }

    public void setLatitud(Point latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(Point longitud) {
        this.longitud = longitud;
    }
}
