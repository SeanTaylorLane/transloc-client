package com.seantaylorlane.translocclient

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_item_stop.view.*

class StopsAdapter(val items: List<Int>) : RecyclerView.Adapter<StopsAdapter.ViewHolder>() {
    val TAG = "StopsAdapter"

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopsAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_stop, parent, false)
        return StopsAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.stop_name.text = items[position].toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stop_name = itemView.stop_name
    }
}