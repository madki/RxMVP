package xyz.madki.rxmvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;

import icepick.Icepick;
import mortar.Presenter;
import mortar.bundler.BundleService;

/**
 * Created by madki on 04/02/16.
 */
public class ActivityPresenter<A extends Activity> extends Presenter<A> {
    @Override
    protected BundleService extractBundleService(A view) {
        return BundleService.getBundleService(view);
    }

    @Override @CallSuper
    protected void onLoad(Bundle savedInstanceState) {
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override @CallSuper
    protected void onSave(Bundle outState) {
        Icepick.saveInstanceState(this, outState);
    }
}
