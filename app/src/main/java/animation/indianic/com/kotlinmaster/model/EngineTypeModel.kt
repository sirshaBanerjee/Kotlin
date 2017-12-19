package animation.indianic.com.kotlinmaster.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by S.B. on 11/27/17.
 */
class EngineTypeModel {

    @SerializedName("engine_id")
    @Expose
    var engineId: String? = null
    @SerializedName("engine_type_name")
    @Expose
    var engineTypeName: String? = null
}