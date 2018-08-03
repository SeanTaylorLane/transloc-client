package com.seantaylorlane.translocclient.ui.screens.map

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.FragmentManager
import android.widget.FrameLayout
import com.seantaylorlane.translocclient.R
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity(), RoutesAdapter.OnRouteClickListener, FragmentManager.OnBackStackChangedListener {
    lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        setSupportActionBar(actionbar_map)
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet))
//        supportFragmentManager.addOnBackStackChangedListener(this)
        supportFragmentManager.beginTransaction()
                .replace(R.id.bottom_sheet, RoutesFragment())
                .commit()
    }

    override fun onRouteClick() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.bottom_sheet, StopsFragment())
                .addToBackStack(null)
                .commit()
    }

    override fun onBackStackChanged() {
        if(supportFragmentManager.backStackEntryCount == 0) {
            // The user pressed the back button and reversed the StopsFragment back to RoutesFragment
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}
