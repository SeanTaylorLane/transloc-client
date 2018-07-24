package com.seantaylorlane.translocclient

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seantaylorlane.translocclient.utils.RecyclerViewDecorators
import kotlinx.android.synthetic.main.fragment_stops.*

class StopsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_stops, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = (1..10).toList()
        rv_stops.apply {
            adapter = StopsAdapter(items)
            layoutManager = LinearLayoutManager(context)
            val divider = ResourcesCompat.getDrawable(resources, R.drawable.list_divider_grey, null)
            addItemDecoration(RecyclerViewDecorators.VerticalDivider(divider))
        }
    }
}