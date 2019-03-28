package rs.gerontoloski.centresuboticaapp.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rs.gerontoloski.centresuboticaapp.retrofit.POJO.media.Media;
import rs.gerontoloski.centresuboticaapp.retrofit.POJO.page.Page;

/**
 * Created by Grigoriy Frank
 */

public interface GerontologyApi {

    @GET("wp-json/wp/v2/pages/{page}")
    Call<Page> getPageById(@Path("page") int pageId);

    @GET("wp-json/wp/v2/pages")
    Call<List<Page>> getTwoPagesByIds(@Query("include[]=") int firstPageId, @Query("include[]=") int secondPageId);

    @GET("wp-json/wp/v2/media/{media}")
    Call<Media> getMediaById(@Path("media") int mediaId);



}
