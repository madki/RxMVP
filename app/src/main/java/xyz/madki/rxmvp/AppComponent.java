package xyz.madki.rxmvp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by madki on 23/12/15.
 */
@Component(modules = {AppModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {
  GitHubClient gitHubClient();
}
