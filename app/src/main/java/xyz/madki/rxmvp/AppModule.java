package xyz.madki.rxmvp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by madki on 02/02/16.
 */
@Module
public class AppModule {
    private App fetchApp;

    public AppModule(App fetchApp) {
        this.fetchApp = fetchApp;
    }

    @Provides @Singleton
    public App providesApplication() {
        return fetchApp;
    }

}
