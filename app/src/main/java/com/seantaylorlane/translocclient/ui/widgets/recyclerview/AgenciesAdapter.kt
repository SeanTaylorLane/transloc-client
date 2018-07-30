package com.seantaylorlane.translocclient.ui.widgets.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.seantaylorlane.translocclient.R
import com.seantaylorlane.translocclient.api.TranslocModels
import kotlinx.android.synthetic.main.rv_item_agency.view.*
import kotlinx.android.synthetic.main.rv_item_route.view.*

class AgenciesAdapter : RecyclerView.Adapter<AgenciesAdapter.ViewHolder>() {
    val TAG = "RoutesAdapter"

    var items: List<TranslocModels.AgenciesResponse.Agency> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_agency, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.agency_name.text = items[position].name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val agency_name: TextView = itemView.agency_name
    }
}