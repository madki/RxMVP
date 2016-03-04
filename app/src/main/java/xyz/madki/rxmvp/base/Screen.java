package xyz.madki.rxmvp.base;

import android.content.Context;
import android.view.View;

import mortar.MortarScope;

/**
 * Created by madki on 08/02/16.
 */
public abstract class Screen<C extends ViewInjector<V>, V extends ScopedView<C, ?>> {

  protected abstract V createView(Context context);

  protected abstract C createComponent(Context context);

  public String getScopeName(int position) {
    return getClass().getName() + position;
  }

  public String getScopeName() {
    return getClass().getName();
  }

  public V instantiateView(Context parentContext) {
    return instantiateView(parentContext, getScopeName());
  }

  public V instantiateView(Context parentContext, int position) {
    return instantiateView(parentContext, getScopeName(position));
  }

  protected V instantiateView(Context parentContext, String scopeName) {
    MortarScope parentScope = MortarScope.getScope(parentContext);
    MortarScope childScope = parentScope.findChild(scopeName);
    if (childScope == null) {
      childScope = parentScope.buildChild()
              .withService(DaggerService.SERVICE_NAME, createComponent(parentContext))
              .build(scopeName);
    }
    Context childContext = childScope.createContext(parentContext);
    return createView(childContext);
  }

  public static void destroyScope(View v) {
    MortarScope.getScope(v.getContext()).destroy();
  }
}
