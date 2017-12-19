package animation.indianic.com.kotlinmaster.util

import android.content.Context
import android.text.TextUtils
import android.view.View
import animation.indianic.com.kotlinmaster.R

/**
 * Created by S.B. on 11/30/17.
 */
object Utils {

    fun showSnackBar(view: View, message: String, isError: Boolean, context: Context) {
        if(TextUtils.isEmpty(message)){
//            message = context.getString(R.string.app_name)
        }
    }
}