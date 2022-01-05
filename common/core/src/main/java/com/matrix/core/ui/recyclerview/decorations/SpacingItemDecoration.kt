/* Android with clean and multi module architecture */
package com.matrix.core.ui.recyclerview.decorations

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Creates a spacing RecyclerView.ItemDecoration that can be used with
 * a LinearLayoutManager.
 */
class SpacingItemDecoration(context: Context, space: Int = 8) : RecyclerView.ItemDecoration() {
    private val spaceInDp = (space * context.resources.displayMetrics.density).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            left = spaceInDp
            right = spaceInDp
            bottom = spaceInDp
        }

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spaceInDp
        }
    }
}
