package animation.indianic.com.kotlinmaster.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by S.B. on 11/25/17.
 */
object Preference {

    var PREF_USER_CART_COUNT: String = "cartCount"

    val sharedPreferences: SharedPreferences? = null

    var preference: Preference? = null

    /**
     * create a static method to get instance
     */
//    fun getInstance(context: Context): Preference? {
//
//        if(preference == null){
//            preference =
//        }
//        return preference
//    }
}