package animation.indianic.com.kotlinmaster.fragment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import animation.indianic.com.kotlinmaster.R
import animation.indianic.com.kotlinmaster.`interface`.ItemClickListener
import animation.indianic.com.kotlinmaster.adapter.SelectionBrandAdapter
import animation.indianic.com.kotlinmaster.util.Constants
import kotlinx.android.synthetic.main.fragment_selection.*

/**
 * Created by S.B. on 11/24/17.
 */
class SelectionFragment : BaseFragment(), ItemClickListener {

    /**
     * this is suppossed to be chnaged later by type unspecified type array and assigned type in runtime
     */
    lateinit var arrModelList: ArrayList<*>

    override fun defineResourceLayout(): Int {
        return R.layout.fragment_selection
    }

    override fun initializeComponent(view: View) {
        fragment_select_brand_tvCancel.setOnClickListener(this)
        if (arguments != null) {
            if (arguments.containsKey(Constants.BUNDLE_BRAND_LIST)) {
                arrModelList = arguments.getSerializable(Constants.BUNDLE_BRAND_LIST) as ArrayList<*>
            } else if (arguments.containsKey(Constants.BUNDLE_MODEL_LIST)) {
                arrModelList = arguments.getSerializable(Constants.BUNDLE_MODEL_LIST) as ArrayList<*>
            } else if (arguments.containsKey(Constants.BUNDLE_ENGINE_TYPE)) {
                arrModelList = arguments.getSerializable(Constants.BUNDLE_ENGINE_TYPE) as ArrayList<*>
            }
        }

            // initialize the adapter
            val selectionAdapter = SelectionBrandAdapter(arrModelList, this@SelectionFragment)
            // set layout for the recycler view
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity.applicationContext)
            fragment_select_brand_rvBrandNames.layoutManager = layoutManager
            fragment_select_brand_rvBrandNames.setHasFixedSize(true)
            fragment_select_brand_rvBrandNames.itemAnimator = DefaultItemAnimator()
            fragment_select_brand_rvBrandNames.adapter = selectionAdapter
    }

    override fun onItemClick( position: Int) {
        val intent: Intent? = Intent()
        intent?.putExtra(Constants.BUNDLE_POSITION, position)
        getTargetFragment().onActivityResult(getTargetRequestCode(), AppCompatActivity.RESULT_OK, intent);
        popBackStack()

    }

    override fun onClick(p0: View) {
        super.onClick(p0)
        when (p0.id) {
            R.id.fragment_select_brand_tvCancel ->
                popBackStack()
        }
    }
}