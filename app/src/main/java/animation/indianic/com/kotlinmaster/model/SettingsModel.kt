package animation.indianic.com.kotlinmaster.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SettingsModel {

    @SerializedName("count")
    @Expose
    var count: String? = null
    @SerializedName("per_page")
    @Expose
    var perPage: String? = null
    @SerializedName("curr_page")
    @Expose
    var currPage: String? = null
    @SerializedName("prev_page")
    @Expose
    var prevPage: String? = null
    @SerializedName("next_page")
    @Expose
    var nextPage: String? = null
    @SerializedName("success")
    @Expose
    var success: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null

}
