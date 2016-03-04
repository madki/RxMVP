package xyz.madki.rxmvp.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by madki on 02/02/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface PerActivity {
}
