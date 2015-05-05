package actions;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Created by Ivan on 01/05/2015.
 */
public class UpdateContextAction extends Action<UpdateContext> {

    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {

        context.args.put("UpdateContext", configuration.value());
        return delegate.call(context);
    }
}
