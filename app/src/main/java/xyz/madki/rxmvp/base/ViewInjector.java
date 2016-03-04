package xyz.madki.rxmvp.base;

/**
 * Created by madki on 09/02/16.
 */
public interface ViewInjector<V extends ScopedView> {
  void inject(V view);
}
