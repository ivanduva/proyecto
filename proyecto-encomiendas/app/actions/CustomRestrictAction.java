package actions;

import be.objectify.deadbolt.java.actions.RestrictAction;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import security.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 01/05/2015.
 */
public class CustomRestrictAction extends Action<CustomRestrict>{
    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {
        final CustomRestrict outerConfig = configuration;
        RestrictAction restrictionsAction = new RestrictAction(configuration.config(),
                this.delegate){

            @Override
            public List<String[]> getRoleGroups()
            {
                List<String[]> roleGroups = new ArrayList<String[]>();
                for (RoleGroup roleGroup : outerConfig.value())
                {
                    TipoUsuario[] value = roleGroup.value();
                    String[] group = new String[value.length];
                    for (int i = 0; i < value.length; i++)
                    {
                        group[i] = value[i].getName();
                    }
                    roleGroups.add(group);
                }
                return roleGroups;
            }

        };
        return restrictionsAction.call(context);
    }
}
