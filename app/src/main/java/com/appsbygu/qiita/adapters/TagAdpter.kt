package com.appsbygu.qiita.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.appsbygu.qiita.R
import com.appsbygu.qiita.models.tag.Tag
import com.squareup.picasso.Picasso

class TagAdapter(tags: ArrayList<Tag>) : RecyclerView.Adapter<TagAdapter.TagViewHolder>(){

    private val tags: ArrayList<Tag> = tags

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TagViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.tag_list, parent, false)
        return TagViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TagViewHolder, position: Int) {
        viewHolder.tagListRankingTextView.text = position.toString()
        viewHolder.tagListPostCountTextView.text = tags[position].itemsCount.toString()
        viewHolder.tagListTitleTextView.text = tags[position].id
        Picasso.get().load(tags[position].iconUrl).into(viewHolder.tagListImageView)
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    class TagViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tagListRankingTextView: TextView = itemView.findViewById(R.id.tagListRankingTextView)
        val tagListTitleTextView: TextView = itemView.findViewById(R.id.tagListTitleTextView)
        val tagListPostCountTextView: TextView = itemView.findViewById(R.id.tagListPostCountTextView)
        val tagListImageView: ImageView = itemView.findViewById(R.id.tagListImageView)
    }
}