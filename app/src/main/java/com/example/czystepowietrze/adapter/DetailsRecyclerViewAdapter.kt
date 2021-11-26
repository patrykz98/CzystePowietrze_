package com.example.czystepowietrze.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.czystepowietrze.fragments.DetailsIdFragment
import com.example.czystepowietrze.R
import com.example.czystepowietrze.api.AirStationJasonItem
import kotlinx.android.synthetic.main.recycler_view_details_item.view.*

class DetailsRecyclerViewAdapter: RecyclerView.Adapter<DetailsRecyclerViewAdapter.ViewHolder>() {

    private var responseList = emptyList<AirStationJasonItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_details_item, parent, false))
    }

    override fun onBindViewHolder(holder: DetailsRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.itemView.textViewStreet.text = responseList[position].addressStreet
        holder.itemView.textViewStationName.text = responseList[position].stationName


        holder.itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val bundle = Bundle()
                bundle.putString("position", responseList[position].id.toString())
                val activity = v!!.context as AppCompatActivity
                val fragment = DetailsIdFragment()
                fragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.mainLayout, fragment).addToBackStack(null).commit()
            }
        })
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    fun setData(newList: List<AirStationJasonItem>){
        responseList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}