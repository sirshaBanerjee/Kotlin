package animation.indianic.com.kotlinmaster.activity

import android.content.Intent
import animation.indianic.com.kotlinmaster.R
import java.util.logging.Handler

/**
 * Created by S.B. on 11/23/17.
 */
class SplashActivity : BaseActivity() {

    private val SPLASH_INTERVAL = 2;
    private val handler: Handler? = null

    override fun initializeComponents() {
        openHomePage()
    }

    override fun defineLayoutResource(): Int {
        return R.layout.activity_splash
    }

    private fun openHomePage(){
        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
        startActivity(intent)
    }
}