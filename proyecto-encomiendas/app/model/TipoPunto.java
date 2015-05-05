package model;

import com.avaje.ebean.annotation.EnumValue;

/**
 * Created by Ivan on 06/04/2015.
 */
public enum TipoPunto {
    @EnumValue("OFICINA ADMINISTRATIVA")
    OFICINA_ADMINISTRATIVA (0),

    @EnumValue("PUNTO EXTERNO")
    PUNTO_EXTERNO (1);

    public final int id;

    TipoPunto(int id) {
        this.id = id;
    }
}
