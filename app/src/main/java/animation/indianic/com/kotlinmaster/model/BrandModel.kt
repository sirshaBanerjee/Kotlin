package animation.indianic.com.kotlinmaster.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by S.B. on 11/27/17.
 */
class BrandModel {

    @SerializedName("brand_name")
    @Expose
    var brandName: String? = null

    @SerializedName("brand_id")
    @Expose
    var brandId: String? = null

    @SerializedName("image")
    @Expose
    var brandImage: String? = null



}