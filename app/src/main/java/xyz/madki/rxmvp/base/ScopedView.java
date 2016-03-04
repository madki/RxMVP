package xyz.madki.rxmvp.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import mortar.ViewPresenter;

/**
 * Created by madki on 08/02/16.
 */
public abstract class ScopedView<C extends ViewInjector, P extends ViewPresenter> extends FrameLayout {
  public ScopedView(Context context) {
    super(context);
    init(context);
  }

  @SuppressWarnings("unchecked")
  protected void init(Context context) {
    inflate(context, getLayoutId(), this);
    if(!isInEditMode()) {
      ButterKnife.bind(this);
      DaggerService.<C>getDaggerComponent(context).inject(this);
    }
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
  }

  @SuppressWarnings("unchecked")
  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if(!isInEditMode()) getPresenter().takeView(this);
  }

  @SuppressWarnings("unchecked")
  @Override protected void onDetachedFromWindow() {
    if(!isInEditMode()) getPresenter().dropView(this);
    super.onDetachedFromWindow();
  }

  @LayoutRes protected abstract int getLayoutId();
  @NonNull protected abstract P getPresenter();
}
