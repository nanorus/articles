package com.nanorus.articles.presentation.ui.articles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bluejamesbond.text.DocumentView
import com.bumptech.glide.Glide
import com.nanorus.articles.R
import com.nanorus.articles.entity.Article

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    private val data = mutableListOf<Article>()

    // list of articles
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(data: List<Article>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        data.getOrNull(position)?.let { article ->
            holder.title.apply {
                this.visibility = View.GONE
                article.title?.let { text ->
                    this.text = text
                    this.visibility = View.VISIBLE
                }
            }
            holder.description.apply {
                this.visibility = View.GONE
                article.description?.let { text ->
                    this.text = text
                    this.visibility = View.VISIBLE
                }
            }
            holder.date.apply {
                this.visibility = View.GONE
                article.getStringDate()?.let { text ->
                    this.text = text
                    this.visibility = View.VISIBLE
                }
            }
            holder.image.apply {
                this.visibility = View.GONE
                article.image?.let { image ->
                    Glide.with(context).load(image).centerCrop().into(this)
                    this.visibility = View.VISIBLE
                }
            }
        }
    }

    class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val description: DocumentView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.image)
        val date: TextView = itemView.findViewById(R.id.date)
    }
}