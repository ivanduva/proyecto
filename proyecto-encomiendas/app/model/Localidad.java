package model;

import javax.persistence.*;

/**
 * Created by Ivan on 16/02/2015.
 */
@Entity
public class Localidad {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long localidadId;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "codigo_postal")
    private Long codigoPostal;

    @OneToOne
    private LatLong ubicacion;
}
