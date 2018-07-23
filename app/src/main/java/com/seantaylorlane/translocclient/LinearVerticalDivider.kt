package com.seantaylorlane.translocclient

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View

class LinearVerticalDivider(val divider: Drawable?) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state) // I don't know why we need to call super's implementation here and not in onDraw
        if(parent?.getChildAdapterPosition(view) == 0) return
        outRect?.top = divider?.intrinsicHeight
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        val dividerLeft = parent?.paddingLeft ?: 0
        val dividerRight = parent?.width?.minus(parent.paddingRight) ?: 0

        val childCount = parent?.childCount ?: 0
        for (i in 0..childCount) {
            val child: View? = parent?.getChildAt(i)

            val params: RecyclerView.LayoutParams? = child?.layoutParams as RecyclerView.LayoutParams?

            val dividerTop = params?.bottomMargin?.plus(child?.bottom ?: 0)
            val dividerBottom = dividerTop?.plus(divider?.intrinsicHeight ?: 0)

            divider?.setBounds(dividerLeft, dividerTop ?: 0, dividerRight, dividerBottom ?: 0)
            divider?.draw(c)
        }
    }
}