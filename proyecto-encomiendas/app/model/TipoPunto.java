package model;

/**
 * Created by Ivan on 06/04/2015.
 */
public enum TipoPunto {
    OFICINA_ADMINISTRATIVA (1), PUNTO_EXTERNO (2);

    public final int id;

    TipoPunto(int id) {
        this.id = id;
    }
}
