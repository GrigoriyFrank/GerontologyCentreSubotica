package rs.gerontoloski.centresuboticaapp;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rs.gerontoloski.centresuboticaapp.retrofit.GerontologyApi;

/**
 * Created by Grigoriy Frank
 */

@SuppressWarnings("JavaDoc")
public class App extends Application {

    private static final String TAG = "App";
    private static GerontologyApi gerontologyApi;


    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gerontoloski.rs/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gerontologyApi = retrofit.create(GerontologyApi.class);

    }


    /**
     * Getter for {@Link GerontologyApi}
     *
     * @return the instance of GerontologyApi interface
     */
    public static GerontologyApi getApi() {
        return gerontologyApi;
    }
}
