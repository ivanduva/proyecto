package model;

import be.objectify.deadbolt.core.models.Role;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Ivan on 01/05/2015.
 */

//Se almacena una instancia de esta clase por rol que se cargue
@Entity
public class SecurityRole extends Model implements Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String name;


    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
