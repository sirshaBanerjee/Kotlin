package animation.indianic.com.kotlinmaster.adapter

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import animation.indianic.com.kotlinmaster.R
import animation.indianic.com.kotlinmaster.fragment.SelectionFragment
import animation.indianic.com.kotlinmaster.model.BrandModel
import animation.indianic.com.kotlinmaster.model.CarModel
import animation.indianic.com.kotlinmaster.model.EngineTypeModel

/**
 * Created by S.B. on 11/24/17.
 */
class SelectionBrandAdapter(val arrBrandList: ArrayList<*>, val selectionFragment: SelectionFragment) : RecyclerView.Adapter<SelectionBrandAdapter.SelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_select_brand, parent, false)
        return SelectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SelectionViewHolder, position: Int) {
        if (arrBrandList.get(position) is BrandModel) {
            holder.tvBrandName.text = ((arrBrandList.get(position) as BrandModel).brandName)
        }
        else if(arrBrandList.get(position) is CarModel){
            holder.tvBrandName.text = ((arrBrandList.get(position) as CarModel).carModelName)
        }
        else if(arrBrandList.get(position) is EngineTypeModel){
            holder.tvBrandName.text = ((arrBrandList.get(position) as EngineTypeModel).engineTypeName)
        }

        holder.tvBrandName.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                selectionFragment.onItemClick(position)
            }
        })
    }

    override fun getItemCount(): Int {
        return arrBrandList.size
    }

    /**
     * to decalre and create view holder in kotin
     */
    inner class SelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvBrandName: AppCompatTextView

        init {

            tvBrandName = itemView.findViewById(R.id.row_select_brand_tvBrandName)
        }

    }
}