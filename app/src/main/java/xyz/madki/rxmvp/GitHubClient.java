package xyz.madki.rxmvp;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by madki on 29/02/16.
 */
public interface GitHubClient {
  String BASE_URL = "https://api.github.com/";

  @GET("users")
  Observable<List<GitHubUser>> users();
}
