package xyz.madki.rxmvp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import javax.inject.Singleton;

import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory;
import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by madki on 23/12/15.
 */
@Module
public class NetworkModule {

  @Provides @Singleton
  public Gson provideGson() {
    return new GsonBuilder()
            .registerTypeAdapterFactory(new AutoParcelGsonTypeAdapterFactory())
            .create();
  }

  @Provides @Singleton
  public OkHttpClient provideOkHttpClient() {
    OkHttpClient client = new OkHttpClient();
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    client.networkInterceptors().add(interceptor);
    return client;
  }

  @Provides @Singleton
  public GitHubClient provideGitHubClient(OkHttpClient client, Gson gson) {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GitHubClient.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    return retrofit.create(GitHubClient.class);
  }
}
