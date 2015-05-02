package actions;

import security.TipoUsuario;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Ivan on 01/05/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleGroup {

    TipoUsuario[] value() default {};

    TipoUsuario[] not() default {};

}
