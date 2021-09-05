package com.example.android.flashnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter( private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)

        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }
    //fills data
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currItem = items[position]
        holder.titleView.text = currItem.title
        holder.authorView.text = currItem.author
        Glide.with(holder.itemView.context).load(currItem.imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews: ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)
//        this function calls the above three functions again
        notifyDataSetChanged()
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.text_view_title)
    val authorView: TextView = itemView.findViewById(R.id.text_view_author)
    val imageView: ImageView = itemView.findViewById(R.id.image_view)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}