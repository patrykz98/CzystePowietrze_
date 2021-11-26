package com.example.czystepowietrze.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.czystepowietrze.R
import com.example.czystepowietrze.api.AirStationDetailsJsonItem
import kotlinx.android.synthetic.main.recycler_view_details_id_item.view.*

class DetailsIDRecyclerViewAdapter: RecyclerView.Adapter<DetailsIDRecyclerViewAdapter.ViewHolder>() {

    private var responseList = emptyList<AirStationDetailsJsonItem>()
    private var responseListParams: ArrayList<String> = ArrayList()
    var isUpdated: Boolean = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsIDRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_details_id_item, parent, false))
    }

    override fun onBindViewHolder(holder: DetailsIDRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.itemView.textViewParamName.text = responseList[position].param.paramName
        holder.itemView.textViewParamCode.text = responseList[position].param.paramCode
        if(isUpdated)
            holder.itemView.textViewParamValue.text = responseListParams[position]

    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    fun setData(newList: List<AirStationDetailsJsonItem>){
        responseList = newList
        notifyDataSetChanged()
    }

    fun setDataParams(listItemParams: String){
        responseListParams.add(listItemParams)
        isUpdated = true
        notifyDataSetChanged()
    }

    fun getDataParams(): ArrayList<String> {
        return responseListParams
    }


//    fun setData(newList: List<AirStationDetailsJsonItem>, listOfParamValue: List<String>){
//        responseList = newList
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}