package com.experion.assignment.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.experion.assignment.R
import com.experion.assignment.models.CaModel
import kotlinx.android.synthetic.main.single_news_item_layout.view.*

class CaRecyclerViewAdapter(
    private var newsList: ArrayList<CaModel>
) :
    RecyclerView.Adapter<CaRecyclerViewAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_news_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = newsList.size

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]

        Glide.with(holder.itemView.context)  //2
            .load(item.imageHref) //3
            .placeholder(R.drawable.ic_placeholder)
            .centerCrop() //4
            .into(holder.itemView.newsImageView) //8
        holder.itemView.titleTextView.text = item.title
        holder.itemView.descriptionTextView.text = item.description
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}