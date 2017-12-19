package animation.indianic.com.kotlinmaster.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

/**
 * Created by S.B. on 11/22/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * Returns the resource id of the layout which will be used for setContentView() for the Activity
     * @return integer
     */
    protected abstract fun defineLayoutResource(): Int

    /**
     * Initialize the components on activity create
     */
    protected abstract fun initializeComponents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(defineLayoutResource())
        initializeComponents()
    }

    /**
     * Adds the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param currentFragment             Current loaded Fragment to be hide
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun addFragment(fragmentContainerResourceId: Int, currentFragment: Fragment?, nextFragment: Fragment?, commitAllowingStateLoss: Boolean): Boolean {

        if (currentFragment == null || nextFragment == null) {
            return false
        }

        // initialize fragment manager : val is final & incase of get support fragment manager we use only this
        val fragmentManager = supportFragmentManager;
        // initialize fragment transaction
        val fragmentTransaction = fragmentManager.beginTransaction()
        // add fragment & for getClass and getSimpleName we will only do this
        fragmentTransaction.add(fragmentContainerResourceId, nextFragment, nextFragment.javaClass.simpleName)
        fragmentTransaction.addToBackStack(nextFragment.javaClass.simpleName)

        // get the parent fragment
        val parentFragment = currentFragment.parentFragment
        fragmentTransaction.hide(parentFragment ?: currentFragment)
        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitNowAllowingStateLoss()
        }
        return true
    }

    /**
     * Replaces the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param fragmentManager             FRAGMENT MANGER
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun replaceFragment(fragmentContainerResourceId: Int, fragmentManager: FragmentManager?, nextFragment: Fragment?, commitAllowingStateLoss: Boolean): Boolean {
        if (fragmentManager == null || nextFragment == null) {
            return false
        }

        // initialize fragment transaction
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentContainerResourceId, nextFragment, nextFragment.javaClass.simpleName)

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitNowAllowingStateLoss()
        }
        return true
    }
}