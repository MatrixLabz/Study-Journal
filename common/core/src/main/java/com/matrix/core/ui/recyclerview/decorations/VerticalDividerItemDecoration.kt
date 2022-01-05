/* Android with clean and multi module architecture */
package com.matrix.core.ui.recyclerview.decorations

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matrix.core.R

/**
 * Creates a vertical divider RecyclerView.ItemDecoration that can be used with
 * a LinearLayoutManager.
 */
class VerticalDividerItemDecoration(context: Context) :
    DividerItemDecoration(context, LinearLayoutManager.VERTICAL) {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            state.itemCount - 1 -> outRect.setEmpty()
            else -> super.getItemOffsets(outRect, view, parent, state)
        }
    }
}

fun defaultDividerItemDecoration(context: Context) =
    VerticalDividerItemDecoration(context).also { decoration ->
        AppCompatResources.getDrawable(context, R.drawable.divider)?.let {
            decoration.setDrawable(it)
        }
    }
