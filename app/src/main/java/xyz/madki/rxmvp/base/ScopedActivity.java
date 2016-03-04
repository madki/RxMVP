package xyz.madki.rxmvp.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

/**
 * Created by madki on 29/02/16.
 */
public abstract class ScopedActivity<C> extends AppCompatActivity {
    @Override public Object getSystemService(@NonNull String name) {
    MortarScope activityScope = MortarScope.findChild(getApplicationContext(), getScopeName());

    if (activityScope == null) {
      activityScope = MortarScope.buildChild(getApplicationContext()) //
              .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
              .withService(DaggerService.SERVICE_NAME, provideComponent())
              .build(getScopeName());
    }

    return activityScope.hasService(name) ? activityScope.getService(name)
            : super.getSystemService(name);
  }

  @Override @CallSuper @SuppressWarnings("unchecked")
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
    setContentView(getLayoutId());
    ButterKnife.bind(this);

    C component = getComponent();
    if (component instanceof ActivityInjector) {
      ((ActivityInjector) component).inject(this);
    }
  }

  @Override @CallSuper protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
  }

  @Override @CallSuper protected void onDestroy() {
    if (isFinishing()) {
      MortarScope activityScope = MortarScope.findChild(getApplicationContext(), getScopeName());
      if (activityScope != null) activityScope.destroy();
    }
    super.onDestroy();
  }

  private String getScopeName() {
    return getClass().getName();
  }

  public C getComponent() {
    return DaggerService.<C>getDaggerComponent(this);
  }

  @NonNull protected abstract C provideComponent();

  @LayoutRes protected abstract int getLayoutId();

}
