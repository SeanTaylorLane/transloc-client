package com.seantaylorlane.translocclient.ui.screens.agencies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.seantaylorlane.translocclient.R
import com.seantaylorlane.translocclient.TranslocApp
import com.seantaylorlane.translocclient.api.Resource
import com.seantaylorlane.translocclient.ui.common.RecyclerViewDecorators
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class AgenciesActivity : AppCompatActivity() {
    val TAG = "AgenciesActivity"
    lateinit var viewModel: AgenciesViewModel
    @Inject
    lateinit var viewModelFactory: AgenciesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(actionbar_main)
        (application as TranslocApp).appComponent.inject(this)

        val agenciesAdapter = AgenciesAdapter()
        rv_agencies.apply {
            layoutManager = LinearLayoutManager(context)
            val divider = ResourcesCompat.getDrawable(resources, R.drawable.list_divider_grey, null)
            addItemDecoration(RecyclerViewDecorators.VerticalDivider(divider))
            adapter = agenciesAdapter
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AgenciesViewModel::class.java)
        viewModel.agencies.observe(this, Observer {
            it?.let {
                when(it.state) {
                    Resource.LOADING -> {
                        // The resource is loading
                        // TODO: Implement progressbar?
                    }
                    Resource.SUCCESS -> {
                        // The resource has successfully been fetched
                        agenciesAdapter.items = it.data!!
                    }
                    Resource.FAILURE -> {
                        // There was an error fetching the resource
                        Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
