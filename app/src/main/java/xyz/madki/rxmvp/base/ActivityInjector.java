package xyz.madki.rxmvp.base;

import android.app.Activity;

/**
 * Created by madki on 09/02/16.
 */
public interface ActivityInjector<A extends Activity> {
  void inject(A activity);
}
