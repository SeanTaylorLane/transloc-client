package com.seantaylorlane.translocclient

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.rv_item_route.view.*

class RoutesAdapter(val items: List<Int>) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {
    val TAG = "RoutesAdapter"

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_route, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoutesAdapter.ViewHolder, position: Int) {
        holder.route_name.text = items[position].toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val route_name: TextView = itemView.route_name
    }

    interface OnRouteClickedListener {
        fun onRouteClick()
    }
}