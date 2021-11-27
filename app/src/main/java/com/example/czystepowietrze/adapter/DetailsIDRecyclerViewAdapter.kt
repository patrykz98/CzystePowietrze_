package com.example.czystepowietrze.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.czystepowietrze.R
import com.example.czystepowietrze.api.AirStationDetailsJsonItem
import com.example.czystepowietrze.api.ParamDetailsJson
import kotlinx.android.synthetic.main.recycler_view_details_id_item.view.*

class DetailsIDRecyclerViewAdapter: RecyclerView.Adapter<DetailsIDRecyclerViewAdapter.ViewHolder>() {

//    private var responseList = emptyList<AirStationDetailsJsonItem>()
    private var responseList = emptyList<ParamDetailsJson>()
    private var responseListParams: ArrayList<String> = ArrayList()

    private var paramValues: ArrayList<String> = ArrayList()
    private var paramKeys: ArrayList<String> = ArrayList()
    var isUpdated: Boolean = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsIDRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_details_id_item, parent, false))
    }

    override fun onBindViewHolder(holder: DetailsIDRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.itemView.textViewParamCode.text = paramKeys[position]
        when(paramKeys[position]){
            "C6H6" -> holder.itemView.textViewParamName.text = "Benzen"
            "SO2" -> holder.itemView.textViewParamName.text = "Dwutlenek siarki"
            "NO2" -> holder.itemView.textViewParamName.text = "Dwutlenek azotu"
            "CO" -> holder.itemView.textViewParamName.text = "Dwutlenek węgla"
            "PM10" -> holder.itemView.textViewParamName.text = "Pył zawieszony"
            "O3" -> holder.itemView.textViewParamName.text = "Ozon"
            "PM2.5" -> holder.itemView.textViewParamName.text = "Pył zawieszony"
        }
        holder.itemView.textViewParamValue.text = paramValues[position]
    }

    override fun getItemCount(): Int {
        return paramKeys.size
    }

    fun setData(paramKeys: ArrayList<String>, paramValues: ArrayList<String>){
        this.paramKeys = paramKeys
        this.paramValues = paramValues
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}