package com.seantaylorlane.translocclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.widget.FrameLayout

class MainActivity : AppCompatActivity(), RoutesAdapter.OnRouteClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val bottomSheetBehavior = BottomSheetBehavior.from(findViewById<FrameLayout>(R.id.bottom_sheet))
        supportFragmentManager.beginTransaction()
                .replace(R.id.bottom_sheet, RoutesFragment())
                .commit()
    }

    override fun onRouteClick() {
        // Stub!
    }
}
