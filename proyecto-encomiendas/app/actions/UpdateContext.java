package actions;

import play.mvc.With;

import java.lang.annotation.*;

/**
 * Created by Ivan on 01/05/2015.
 */
@With(UpdateContextAction.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Documented
public @interface UpdateContext {

    String value();

}
