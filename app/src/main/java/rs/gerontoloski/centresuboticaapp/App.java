package rs.gerontoloski.centresuboticaapp;


import android.app.Application;
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
