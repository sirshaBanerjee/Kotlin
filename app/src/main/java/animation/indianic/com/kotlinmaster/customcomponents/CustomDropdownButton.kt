package animation.indianic.com.kotlinmaster.customcomponents

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import animation.indianic.com.kotlinmaster.R
import kotlinx.android.synthetic.main.custom_button.view.*

/**
 * Created by S.B. on 11/23/17.
 */
class CustomDropdownButton : LinearLayout {

    var bvText: String = ""
    var bvHint: String = ""

    constructor(context: Context?) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        /**
         * initialize a typed array
         */
        val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomDropdownButton, 0, 0)
        /**
         * set texts in the custom button
         */
        try {
            // get the text and drawable specified using the names in attrs.xml
            if(!TextUtils.isEmpty(typedArray.getString(R.styleable.CustomDropdownButton_button_text))) {
                bvText = typedArray.getString(R.styleable.CustomDropdownButton_button_text)
                bvHint = typedArray.getString(R.styleable.CustomDropdownButton_button_hint)
            }

        }
        finally {
            typedArray.recycle()
        }
        LayoutInflater.from(context).inflate(R.layout.custom_button, this)

        // set text in left textview
        custom_button_tvDropdown.setText(bvText)
        custom_button_tvDropdown.setText(bvHint)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {


    }

    /**
     * getter setter of button text
     */

    fun setButtonText(buttonText : String){
        custom_button_tvDropdown.setText(buttonText)
    }

    fun getButtonText(): String {
        return custom_button_tvDropdown.text.toString()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int, defStyleRes: Int) : super(context, attrs, defStyle, defStyleRes) {

    }
}