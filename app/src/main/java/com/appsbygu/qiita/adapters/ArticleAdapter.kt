package com.appsbygu.qiita.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.appsbygu.qiita.R
import com.appsbygu.qiita.models.article.Article
import com.squareup.picasso.Picasso

class ArticleAdapter(private val articles: ArrayList<Article>, val resource: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onclickCallback: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(resource, parent, false)
        return when (resource) {
            R.layout.item_list -> ListContentHolder(itemView)
            R.layout.item_tile -> TileContentHolder(itemView)
            else -> CardContentHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = articles[position]
        val userId = "by ${article.user!!.id}"
        when (resource) {
            R.layout.item_list -> {
                holder as ListContentHolder
                holder.itemListTitleTextView.text = article.title
                Picasso.get().load(article.user!!.profileImageUrl).into(holder.itemListImageView)
                holder.itemListUserNameTextView.text = userId
                holder.itemListSuggestTextView.text = article.likesCount.toString()
                holder.itemListLayout.setOnClickListener { onclickCallback.invoke(article.renderedBody!!) }
            }
            R.layout.item_tile -> {
                holder as TileContentHolder
                holder.itemTileTitleTextView.text = article.title
                Picasso.get().load(article.user!!.profileImageUrl).into(holder.itemTileImageView)
                holder.itemTileUserNameTextView.text = userId
                holder.itemTileSuggestTextView.text = article.likesCount.toString()
                holder.itemTileRelativeLayout.setOnClickListener { onclickCallback.invoke(article.renderedBody!!) }
            }
            R.layout.item_card -> {
                holder as CardContentHolder
                holder.itemCardTextView.text = article.title
                Picasso.get().load(article.user!!.profileImageUrl).into(holder.itemCardImageView)
                holder.itemCardUserNameTextView.text = userId
                holder.itemCardSuggestTextView.text = article.likesCount.toString()
                holder.itemCardRelativeLayout.setOnClickListener { onclickCallback.invoke(article.renderedBody!!) }
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun addArticles(articles: ArrayList<Article>) {
        this.articles.addAll(articles)
    }

    fun setOnclickCallback(callback: ((String) -> Unit)) {
        onclickCallback = callback
    }

    class TileContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTileTitleTextView: TextView = itemView.findViewById(R.id.itemTileTitleTextView)
        val itemTileImageView: ImageView = itemView.findViewById(R.id.itemTileImageView)
        val itemTileUserNameTextView: TextView = itemView.findViewById(R.id.itemTileUserNameTextView)
        val itemTileSuggestTextView: TextView = itemView.findViewById(R.id.itemTileSuggestTextView)
        var itemTileRelativeLayout: RelativeLayout = itemView.findViewById(R.id.itemTileRelativeLayout)
    }

    class ListContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemListTitleTextView: TextView = itemView.findViewById(R.id.itemListTitleTextView)
        val itemListImageView: ImageView = itemView.findViewById(R.id.itemListImageView)
        val itemListUserNameTextView: TextView = itemView.findViewById(R.id.itemListUserNameTextView)
        val itemListSuggestTextView: TextView = itemView.findViewById(R.id.itemListSuggestTextView)
        val itemListLayout: ConstraintLayout = itemView.findViewById(R.id.itemListLayout)
    }

    class CardContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCardTextView: TextView = itemView.findViewById(R.id.itemCardTextView)
        val itemCardImageView: ImageView = itemView.findViewById(R.id.itemCardImageView)
        val itemCardUserNameTextView: TextView = itemView.findViewById(R.id.itemCardUserNameTextView)
        val itemCardSuggestTextView: TextView = itemView.findViewById(R.id.itemCardSuggestTextView)
        var itemCardRelativeLayout: RelativeLayout = itemView.findViewById(R.id.itemCardRelativeLayout)
    }
}