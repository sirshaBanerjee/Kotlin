package animation.indianic.com.kotlinmaster.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import animation.indianic.com.kotlinmaster.R
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.toolbar_home.*
import animation.indianic.com.kotlinmaster.activity.HomeActivity
import animation.indianic.com.kotlinmaster.model.BrandModel
import animation.indianic.com.kotlinmaster.model.CarModel
import animation.indianic.com.kotlinmaster.model.EngineTypeModel
import animation.indianic.com.kotlinmaster.model.SettingsModel
import animation.indianic.com.kotlinmaster.util.Constants
import animation.indianic.com.kotlinmaster.webservice.WSUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody;
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException


/**
 * Created by S.B. on 11/23/17.
 */
class SearchFragment : BaseFragment() {

    var arrBrandModelList: ArrayList<BrandModel>? = null
    var arrCarModelList: ArrayList<CarModel>? = null
    var arrEngineTypeModelList: ArrayList<EngineTypeModel>? = null

    var brandID = ""


    override fun defineResourceLayout(): Int {
        return R.layout.fragment_search
    }

    override fun initializeComponent(view: View) {
        toolbar_home_tvCartCount.visibility = View.GONE
        toolbar_home_ibDrawer.setOnClickListener(this)
        toolbar_home_ibCart.setOnClickListener(this)
        fragment_search_bvCarBrand.setOnClickListener(this)
        fragment_search_bvCarModel.setOnClickListener(this)
        fragment_search_bvEngineType.setOnClickListener(this)
        fragment_search_ibSearch.setOnClickListener(this)
        fragment_search_bvCancel.setOnClickListener(this)
        fragment_search_bvCarBrand.setOnClickListener(this)
        fragment_search_bvCarModel.setOnClickListener(this)
        fragment_search_bvEngineType.setOnClickListener(this)
        toolbar_home_tvTitle.setText(getString(R.string.label_dashboard))
        fragment_search_bvCarBrand.setButtonText(getString(R.string.hint_car_brand))
        fragment_search_bvCarModel.setButtonText(getString(R.string.hint_car_model))
        fragment_search_bvEngineType.setButtonText(getString(R.string.hint_engine_type))


//        callBrandListWS()
    }

    /**
     * call webservice for brandmodels
     */
    fun callBrandListWS() {
        val progressDialog = ProgressDialog(activity, R.style.StyleProgressDialog)

        if (!activity.isFinishing) {
            progressDialog.setCancelable(false)
            progressDialog.show()
            progressDialog.setContentView(R.layout.progress_indicator)
        }

        WSUtils.getClient()?.brandListing()?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                progressDialog.dismiss()

                try {

                    val responseString = response?.body()?.string()
                    val jsonObject = JSONObject(responseString)
                    val settingModel = Gson().fromJson(jsonObject.optJSONObject("settings").toString(), SettingsModel::class.java)
                    if (settingModel != null) {
                        if (settingModel.success.equals("1")) {
                            val listType = object : TypeToken<List<BrandModel>>() {

                            }.type

                            arrBrandModelList = Gson().fromJson(jsonObject.optJSONArray("data").toString(), listType)

                        } else if (!TextUtils.isEmpty(settingModel.message)) {
                            Log.v("tag", settingModel.message)
                        } else {
                            Log.v("tag", "Something went worng")
                        }
                    }

                } catch (e: JSONException) {

                } catch (e: IOException) {

                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                progressDialog.dismiss()
            }

        })

    }

    /**
     * call car model webservice
     */
    fun callCarModelListWS(brandID: String) {
        val progressDialog: ProgressDialog = ProgressDialog(activity, R.style.StyleProgressDialog)

        if (!activity.isFinishing) {
            progressDialog.setCancelable(false)
            progressDialog.show()
            progressDialog.setContentView(R.layout.progress_indicator)
        }

        WSUtils.getClient()?.carListing(brandID)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                progressDialog.dismiss()

                try {

                    val responseString = response?.body()?.string()
                    val jsonObject = JSONObject(responseString)
                    val settingModel = Gson().fromJson(jsonObject.optJSONObject("settings").toString(), SettingsModel::class.java)
                    if (settingModel != null) {
                        if (settingModel.success.equals("1")) {
                            val listType = object : TypeToken<List<CarModel>>() {

                            }.type

                            arrCarModelList = Gson().fromJson(jsonObject.optJSONArray("data").toString(), listType)

                        } else if (!TextUtils.isEmpty(settingModel.message)) {
                            Toast.makeText(activity, "message" + settingModel.message, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(activity, "Something went worng", Toast.LENGTH_SHORT).show()
                        }
                    }

                } catch (e: JSONException) {

                } catch (e: IOException) {

                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                progressDialog.dismiss()
            }

        })
    }

    /**
     * call car model webservice
     */
    fun callEngineTypeWS(brandID: String, carModelID: String) {
        val progressDialog: ProgressDialog = ProgressDialog(activity, R.style.StyleProgressDialog)

        if (!activity.isFinishing) {
            progressDialog.setCancelable(false)
            progressDialog.show()
            progressDialog.setContentView(R.layout.progress_indicator)
        }

        WSUtils.getClient()?.engineTypeListing(brandID, carModelID)?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                progressDialog.dismiss()

                try {

                    val responseString = response?.body()?.string()
                    val jsonObject = JSONObject(responseString)
                    val settingModel = Gson().fromJson(jsonObject.optJSONObject("settings").toString(), SettingsModel::class.java)
                    if (settingModel != null) {
                        if (settingModel.success.equals("1")) {
                            val listType = object : TypeToken<List<EngineTypeModel>>() {

                            }.type

                            arrEngineTypeModelList = Gson().fromJson(jsonObject.optJSONArray("data").toString(), listType)

                        } else if (!TextUtils.isEmpty(settingModel.message)) {
                            Toast.makeText(activity, "message" + settingModel.message, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(activity, "Something went worng", Toast.LENGTH_SHORT).show()
                        }
                    }

                } catch (e: JSONException) {

                } catch (e: IOException) {

                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                progressDialog.dismiss()
            }

        })
    }

    override fun onClick(view: View) {
        super.onClick(view)
        val bundle: Bundle? = Bundle()
        when (view.id) {
            R.id.toolbar_home_ibDrawer -> {

                /**
                 * to call a method which is inside HomeActivity() we cast getActivity as home activity
                 *((HomeActivity) getActivity()).openDrawer() // like this in java
                 */
                (activity as HomeActivity).openDrawer()
            }
            R.id.toolbar_home_ibCart -> {
                /**
                 * to add a new fragment over this fragment in the same container
                 */

                // declare a new fragment
                val myBasketFragment = MyBasketFragment()
                // set target fragment
                myBasketFragment.setTargetFragment(this@SearchFragment, Constants.REQUEST_CODE_RELOAD_FRAGMENT)
                addFragment(R.id.activity_home_flContainer, this@SearchFragment, myBasketFragment, false)
            }
            R.id.fragment_search_bvCarModel -> {
                val selectionFragment = SelectionFragment()
                bundle?.putSerializable(Constants.BUNDLE_MODEL_LIST, arrCarModelList)
                selectionFragment.arguments = bundle
                selectionFragment.setTargetFragment(this@SearchFragment, Constants.REQUEST_CODE_CAR_MODEL)
                addFragment(R.id.activity_home_flContainer, this@SearchFragment, selectionFragment, false)
            }
            R.id.fragment_search_bvCarBrand -> {
                val selectionFragment = SelectionFragment()
                bundle?.putSerializable(Constants.BUNDLE_BRAND_LIST, arrBrandModelList)
                selectionFragment.arguments = bundle
                selectionFragment.setTargetFragment(this@SearchFragment, Constants.REQUEST_CODE_BRAND)
                addFragment(R.id.activity_home_flContainer, this@SearchFragment, selectionFragment, false)
            }
            R.id.fragment_search_bvEngineType -> {
                val selectionFragment = SelectionFragment()
                bundle?.putSerializable(Constants.BUNDLE_ENGINE_TYPE, arrEngineTypeModelList)
                selectionFragment.arguments = bundle
                selectionFragment.setTargetFragment(this@SearchFragment, Constants.REQUEST_CODE_ENGINE_TYPE)
                addFragment(R.id.activity_home_flContainer, this@SearchFragment, selectionFragment, false)
            }
            R.id.fragment_search_ibSearch -> {

            }

            R.id.fragment_search_bvCancel -> {
                callBrandListWS()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
            if (requestCode == Constants.REQUEST_CODE_BRAND && arrBrandModelList != null) {
                val position = data.getIntExtra(Constants.BUNDLE_POSITION, 0)
                val pos = Integer.parseInt(position.toString())
                fragment_search_bvCarBrand.setButtonText(arrBrandModelList!![pos].brandName!!)
                brandID = arrBrandModelList!![pos].brandId.toString()
                callCarModelListWS(brandID)
            } else if (requestCode == Constants.REQUEST_CODE_CAR_MODEL && arrCarModelList != null) {
                val position = data.getIntExtra(Constants.BUNDLE_POSITION, 0)
                val pos = Integer.parseInt(position.toString())
                fragment_search_bvCarModel.setButtonText(arrCarModelList!![pos].carModelName!!)
                val carModelID = arrCarModelList!![pos].carModelId.toString()
                if (!TextUtils.isEmpty(brandID)) {
                    callEngineTypeWS(brandID, carModelID)
                }
            } else if (requestCode == Constants.REQUEST_CODE_ENGINE_TYPE && arrEngineTypeModelList != null) {
                val position = data.getIntExtra(Constants.BUNDLE_POSITION, 0)
                val pos = Integer.parseInt(position.toString())
                fragment_search_bvEngineType.setButtonText(arrEngineTypeModelList!![pos].engineTypeName!!)
            }
        }

    }

}