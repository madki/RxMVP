package xyz.madki.rxmvp;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import xyz.madki.rxmvp.base.ActivityInjector;
import xyz.madki.rxmvp.base.PerActivity;
import xyz.madki.rxmvp.base.PresentedActivity;

public class MainActivity extends PresentedActivity<MainActivity.Component, MainPresenter> {
  @Inject MainPresenter presenter;

  @NonNull @Override protected Component provideComponent() {
    return DaggerMainActivity_Component.builder()
            .appComponent(App.component(this))
            .build();
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @NonNull @Override protected MainPresenter getPresenter() {
    return presenter;
  }

  @PerActivity
  @dagger.Component(dependencies = AppComponent.class)
  public interface Component extends ActivityInjector<MainActivity> {
    @Override void inject(MainActivity activity);
  }

}
