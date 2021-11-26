package com.example.czystepowietrze.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.czystepowietrze.FragmentAgent
import com.example.czystepowietrze.R

class MenuRecyclerAdapter : RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder>() {

    private var titles = arrayOf("Dostępne stacje pomiarowe", "Szczegółowe informacje ")
    private var subtitles = arrayOf("Zobacz mape \ndostępnych stacji \npomiarowych ▶", "Zobacz szczegółowe \ninformacje stacji pomiarowych \nwybranych z listy ▶")

    private var urls = arrayOf("https://img.freepik.com/free-vector/fighting-deforestation-earth-banner-flat-vector-illustration-isolated_181313-2329.jpg?size=626&ext=jpg",
                                "https://image.freepik.com/free-vector/global-co2-emissions-abstract-concept_335657-3204.jpg")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuRecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemSubtitle.text = subtitles[position]

        Glide.with(holder.itemView)
            .load(urls[position])
            .centerCrop()
            .placeholder(R.drawable.airimage)
            .into(holder.itemImage)


        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, FragmentAgent::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return titles.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemTitle: TextView
        var itemSubtitle: TextView
        var itemImage: ImageView

        init {
            itemTitle = itemView.findViewById(R.id.itemRvTitle)
            itemSubtitle = itemView.findViewById(R.id.itemRvSubtitle)
            itemImage = itemView.findViewById(R.id.itemImage)
        }


    }

}