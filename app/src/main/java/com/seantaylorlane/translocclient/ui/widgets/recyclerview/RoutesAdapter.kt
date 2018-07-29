package com.seantaylorlane.translocclient.ui.widgets.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.seantaylorlane.translocclient.R
import kotlinx.android.synthetic.main.rv_item_route.view.*

class RoutesAdapter(val items: List<Int>, val routeClickListener: OnRouteClickListener) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {
    val TAG = "RoutesAdapter"

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_route, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.route_name.text = items[position].toString()
        holder.itemView.setOnClickListener { routeClickListener.onRouteClick() }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val route_name: TextView = itemView.route_name
    }

    interface OnRouteClickListener {
        fun onRouteClick()
    }
}