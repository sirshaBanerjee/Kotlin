package animation.indianic.com.kotlinmaster.activity

import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import animation.indianic.com.kotlinmaster.R
import animation.indianic.com.kotlinmaster.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by S.B. on 11/23/17.
 */
class HomeActivity : BaseActivity(), DrawerLayout.DrawerListener {


    override fun initializeComponents() {
        /**
         * as we have kotlinx plugin we do not need to find every view by it's id
         */
        activity_home_dl_navigation.addDrawerListener(this)
        activity_home_dl_navigation.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);// Lock the drawer to disallow User to swipe to open

        val searchFragment = SearchFragment()
        replaceFragment(R.id.activity_home_flContainer, getSupportFragmentManager(), searchFragment, false);
    }

    override fun defineLayoutResource(): Int {
        return R.layout.activity_home
    }

    override fun onDrawerStateChanged(newState: Int) {
    }

    override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
    }

    override fun onDrawerClosed(drawerView: View?) {
        activity_home_dl_navigation.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);// Lock the drawer to disallow User to swipe to open
    }

    override fun onDrawerOpened(drawerView: View?) {
        activity_home_dl_navigation.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);// Un Lock the drawer to allow User to swipe to close
    }

    fun openDrawer() {
        activity_home_dl_navigation.openDrawer(Gravity.START)
    }

}