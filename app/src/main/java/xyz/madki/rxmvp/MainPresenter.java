package xyz.madki.rxmvp;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import xyz.madki.rxmvp.base.ActivityPresenter;
import xyz.madki.rxmvp.base.PerActivity;

/**
 * Created by madki on 29/02/16.
 */
@PerActivity
public class MainPresenter extends ActivityPresenter<MainActivity> {
  GitHubClient gitHubClient;

  @Inject
  public MainPresenter(GitHubClient gitHubClient) {
    this.gitHubClient = gitHubClient;
  }

  @Override protected void onLoad(Bundle savedInstanceState) {
    super.onLoad(savedInstanceState);
    Toast.makeText(getView(), "Presenter loaded", Toast.LENGTH_LONG).show();
  }
}
