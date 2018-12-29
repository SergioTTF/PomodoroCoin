package com.example.sergiotorres.pomodorocoinapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.tag_item.view.*

class TagAdapter(private val tagList: List<Tag>, private val context:Context) : Adapter<TagAdapter.TagHolder>() {

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        val tag = tagList[position]
        holder.tagTitle.text = tag.name
        holder.tagStreak.text = tag.streak.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tag_item, parent,false)
        return TagHolder(view)
    }


    override fun getItemCount(): Int {
        return tagList.size
    }

    class TagHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tagTitle = itemView.tagTitle
        val tagBar = itemView.tagBar
        val tagStreak = itemView.tagStreak

        fun bindView(tag:Tag) {
            tagTitle.text = tag.name
            tagStreak.text = tag.streak.toString()
        }
    }
}