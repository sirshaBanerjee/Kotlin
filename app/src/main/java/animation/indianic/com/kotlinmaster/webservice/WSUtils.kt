package animation.indianic.com.kotlinmaster.webservice

/**
 * Created by S.B. on 11/27/17.
 */

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

object WSUtils {

    var wsCallBack: WSCallBacks? = null

    /**
     * Static method to to get api client instance
     *
     * @return ApiCallback instance
     */
    fun getClient(): WSCallBacks? {
        try {
            if (wsCallBack == null) {
                val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                httpClient.connectTimeout(60, TimeUnit.SECONDS)
                httpClient.readTimeout(60, TimeUnit.SECONDS)
                /**
                 *  interceptor for printing logs of request and response
                 */
//                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//                httpClient.addInterceptor(logging);  // <-- this is the important line!

                val client = Retrofit.Builder()
                        .baseUrl("http://php54.indianic.com/autopart_selling/WS/")
                        .client(httpClient.build())
                        .build()
                wsCallBack = client.create(WSCallBacks::class.java)
                return wsCallBack
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return wsCallBack
    }

    fun getFileMultipart(context: Context, partName: String, file: File): MultipartBody.Part {

        // create RequestBody instance from file
        val requestFile: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile)
    }
}