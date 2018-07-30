package com.seantaylorlane.translocclient.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import com.seantaylorlane.translocclient.AgenciesViewModel
import com.seantaylorlane.translocclient.R
import com.seantaylorlane.translocclient.ui.widgets.recyclerview.AgenciesAdapter
import com.seantaylorlane.translocclient.ui.widgets.recyclerview.RecyclerViewDecorators
import kotlinx.android.synthetic.main.activity_main.*

class AgenciesActivity : AppCompatActivity() {
    lateinit var viewModel: AgenciesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(actionbar_main)

        rv_agencies.apply {
            layoutManager = LinearLayoutManager(context)
            val divider = ResourcesCompat.getDrawable(resources, R.drawable.list_divider_grey, null)
            addItemDecoration(RecyclerViewDecorators.VerticalDivider(divider))
        }

        viewModel = ViewModelProviders.of(this).get(AgenciesViewModel::class.java)
        viewModel.agencies.observe(this, Observer {
            rv_agencies.adapter = AgenciesAdapter(it ?: emptyList())
        })
    }

    private fun onError(message: String) {

    }
}
