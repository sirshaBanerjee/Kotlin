package animation.indianic.com.kotlinmaster.webservice

import org.json.JSONArray;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by S.B. on 11/27/17.
 */
interface WSCallBacks {

    @GET("brand_listing")
    fun brandListing() : Call<ResponseBody>

    @GET("car_model_listing")
    fun carListing(@Query("brand_id") brandID : String) : Call<ResponseBody>

    @GET("car_engine_type_listing")
    fun engineTypeListing(@Query("brand_id") brandID : String, @Query("car_model_id") carModelID : String) : Call<ResponseBody>
}