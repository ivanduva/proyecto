package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ivan on 05/04/2015.
 */
@Entity
@DiscriminatorValue(value = "VE")
public class ViajeEncomienda extends Servicio {

    //@OneToMany (cascade = CascadeType.ALL)
    //private List<Encomienda> encomiendas;

    public ViajeEncomienda(Long servicioId, String descripcion, String nombre, boolean habilitado, List<FranjaHoraria> franjasHorarias) {
        super(servicioId, descripcion, nombre, habilitado, franjasHorarias);
        //this.encomiendas = encomiendas;
    }

    //public List<Encomienda> getEncomiendas() {
      //  return encomiendas;
    //}

    //public void setEncomiendas(List<Encomienda> encomiendas) {
      //  this.encomiendas = encomiendas;
    //}
}
