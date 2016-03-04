package xyz.madki.rxmvp;

import auto.parcelgson.AutoParcelGson;
import auto.parcelgson.gson.annotations.SerializedName;

/**
 * Created by madki on 29/02/16.
 */
@AutoParcelGson
public abstract class GitHubUser {
  public abstract long id();
  @SerializedName("login")
  public abstract String name();
  @SerializedName("avatar_url")
  public abstract String image();
}
