package model;

import be.objectify.deadbolt.core.models.Permission;
import play.db.ebean.Model;

import javax.persistence.*;


/**
 * Created by Ivan on 01/05/2015.
 */

//Se almacena una instancia de esta clase por permiso que se cargue
@Entity
public class UserPermission extends Model implements Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "permission_value")
    public String value;

    public String getValue()
    {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
