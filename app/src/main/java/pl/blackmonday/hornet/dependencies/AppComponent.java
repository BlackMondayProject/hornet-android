package pl.blackmonday.hornet.dependencies;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Marcin Laskowski on 30.09.16.
 * Senfino 2016
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(DependencyContainer dependencies);
}
