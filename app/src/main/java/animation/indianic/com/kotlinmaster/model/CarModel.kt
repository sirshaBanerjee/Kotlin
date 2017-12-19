package animation.indianic.com.kotlinmaster.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by S.B. on 11/27/17.
 */
class CarModel {

    @SerializedName("car_model_id")
    @Expose
    var carModelId: String? = null
    @SerializedName("car_model_name")
    @Expose
    var carModelName: String? = null
}