package xyz.madki.rxmvp.base;

import android.support.annotation.NonNull;

/**
 * Created by madki on 02/02/16.
 */
public abstract class PresentedActivity<C, P extends ActivityPresenter> extends ScopedActivity<C> {

  @Override @SuppressWarnings("unchecked")
  protected void onResume() {
    super.onResume();
    getPresenter().takeView(this);
  }

  @Override @SuppressWarnings("unchecked")
  protected void onPause() {
    super.onPause();
    getPresenter().dropView(this);
  }

  @NonNull protected abstract P getPresenter();
}
