package animation.indianic.com.kotlinmaster.fragment

import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import animation.indianic.com.kotlinmaster.activity.BaseActivity
import animation.indianic.com.kotlinmaster.util.Constants

/**
 * Created by S.B. on 11/22/17.
 */
abstract class BaseFragment : Fragment(), View.OnClickListener {

    /**
     * Contains last clicked time
     */
    protected var lastClickedItem: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(defineResourceLayout(), container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent(view)
    }

    /**
     * Returns the resource id of the layout which will be used for setContentView() for the Activity
     * @return integer
     */
    protected abstract fun defineResourceLayout(): Int

    /**
     * Initialize the components on activity create
     */
    protected abstract fun initializeComponent(view: View)

    /**
     * Adds the Fragment into layout container.
     *
     * @param container               Resource id of the layout in which Fragment will be added
     * @param currentFragment         Current loaded Fragment to be hide
     * @param nextFragment            New Fragment to be loaded into container
     * @param requiredAnimation       true if screen transition animation is required
     * @param commitAllowingStateLoss true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws ClassCastException    Throws exception if getActivity() is not an instance of BaseActivity
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun addFragment(container: Int, currentFragment: Fragment, nextFragment: Fragment, commitAllowingStateLoss: Boolean): Boolean {
        if (activity != null) {
            if (activity is BaseActivity) {
                (activity as BaseActivity).addFragment(container, currentFragment, nextFragment, commitAllowingStateLoss)
            } else {
                throw ClassCastException(BaseActivity::class.java.name + "cannot be cast into" + activity.javaClass.name)
            }
        }
        return true
    }

    protected fun popBackStack() {
        if (activity != null) {
            val fragmentManager = activity.supportFragmentManager;
            fragmentManager.popBackStackImmediate()
        }
    }

    override fun onClick(p0: View) {
        /**
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - lastClickedItem > Constants.MAX_CLICK_INTERVAL) {
            return
        }
        lastClickedItem = SystemClock.elapsedRealtime()
    }
}