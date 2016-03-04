package xyz.madki.rxmvp.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by madki on 09/02/16.
 */
public abstract class ScreenPagerAdapter extends PagerAdapter {

  @Override public boolean isViewFromObject(View view, Object object) {
    return view.equals(object);
  }

  @Override public View instantiateItem(ViewGroup container, int position) {
    Screen s = createScreen(position);
    View v = s.instantiateView(container.getContext(), position);
    container.addView(v);
    return v;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    View v = (View) object;
    Screen.destroyScope(v);
    container.removeView(v);
  }

  public abstract Screen createScreen(int position);
}
