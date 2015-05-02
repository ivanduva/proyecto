package security;

import be.objectify.deadbolt.core.models.Permission;

/**
 * Created by Ivan on 01/05/2015.
 */
public enum TipoPermiso implements Permission {
    ;

    @Override
    public String getValue() {
        return name().toString();
    }
}
