package xyz.madki.rxmvp;

import android.content.Context;

import timber.log.Timber;
import xyz.madki.rxmvp.base.MortarApp;

/**
 * Created by madki on 29/02/16.
 */
public class App extends MortarApp {
  private AppComponent component;

  @Override
  public void onCreate() {
    super.onCreate();
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    component = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
  }

  public static AppComponent component(Context context) {
    return ((App) context.getApplicationContext()).component;
  }
}
