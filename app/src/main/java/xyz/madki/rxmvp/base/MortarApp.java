package xyz.madki.rxmvp.base;

import android.app.Application;

import mortar.MortarScope;

/**
 * Created by madki on 29/02/16.
 */
public class MortarApp extends Application {
  private MortarScope rootScope;

    @Override
    public Object getSystemService(String name) {
        if (rootScope == null) rootScope = MortarScope.buildRootScope().build("Root");

        return rootScope.hasService(name) ? rootScope.getService(name) : super.getSystemService(name);
    }

}
