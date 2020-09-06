package com.hgianastasio.biblioulbrav2.views.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by heitor_12 on 31/05/17.
 */
class FABHideOnScrollBehavior(context: Context?, attrs: AttributeSet?) : FloatingActionButton.Behavior(context, attrs) {
    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, `as`: Int, a: IntArray) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, `as`, a)

        //child -> Floating Action Button
        if (dyConsumed > 0) {
            val layoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
            val fabBottomMargin = layoutParams.bottomMargin
            child.animate().translationY(child.height + fabBottomMargin.toFloat()).setInterpolator(LinearInterpolator()).start()
        } else if (dyConsumed < 0) {
            child.animate().translationY(0f).setInterpolator(LinearInterpolator()).start()
        }
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }
}