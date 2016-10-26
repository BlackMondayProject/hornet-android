package pl.blackmonday.hornet.dependencies;

import javax.inject.Inject;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.App;

/**
 * Created by Marcin Laskowski on 30.09.16.
 * Senfino 2016
 */


public class DependencyContainer {

    @Inject
    public IApi api;

    public DependencyContainer(){
        App.dependencies().inject(this);
    }

}
