package com.example.czystepowietrze.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.czystepowietrze.R
import com.example.czystepowietrze.api.AirStationJasonItem
import com.example.czystepowietrze.fragments.DetailsIdFragment
import kotlinx.android.synthetic.main.recycler_view_details_item.view.*

class SearchByCityRecyclerAdapter: RecyclerView.Adapter<SearchByCityRecyclerAdapter.ViewHolder>() {

    private var responseList = emptyList<AirStationJasonItem>()
    private lateinit var city: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_details_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.textViewStreet.text = responseList[position].addressStreet
            holder.itemView.textViewStationName.text = responseList[position].stationName

        holder.itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val bundle = Bundle()
                bundle.putString("position", responseList[position].id.toString())
                bundle.putString("city", responseList[position].stationName)
                val activity = v!!.context as AppCompatActivity
                val fragment = DetailsIdFragment()
                fragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.mainLayout, fragment).addToBackStack(null).commit()
            }
        })
    }

    fun filter(city: String){
        responseList = responseList.filter{ value ->  value.stationName.lowercase().contains(city)}
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    fun setData(newList: List<AirStationJasonItem>, city: String){
        this.city = city
        responseList = newList
        notifyDataSetChanged()
        filter(city)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


}