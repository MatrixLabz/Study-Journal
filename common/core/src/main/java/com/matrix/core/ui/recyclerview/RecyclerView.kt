/* Android with clean and multi module architecture */
package com.matrix.core.ui.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Fully visible Recyclerview item is at 0 position.
 */
const val ITEM_IS_ON_TOP = 0

/**
 * Fully visible Recyclerview item is at the last position.
 */
const val ITEM_IS_AT_BOTTOM = 1

/**
 * Fully visible Recyclerview item is neither at zero position nor last position.
 */
const val OTHER_POSITION = -1

/**
 * Returns the adapter position of the fully visible view.
 */
fun RecyclerView.findItemPosition(position: Int) = with(layoutManager as LinearLayoutManager) {
    when {
        position == 0 && position == findFirstCompletelyVisibleItemPosition() ->
            ITEM_IS_ON_TOP
        position == itemCount - 1 && position - 1 == findLastCompletelyVisibleItemPosition() ->
            ITEM_IS_AT_BOTTOM
        else ->
            OTHER_POSITION
    }
}
