/* Android with clean and multi module architecture */
package com.matrix.core.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil

/**
 * Implementation of [DiffUtil.Callback] for the list.
 */
class ListDiffUtil<T>(private val old: List<T>, private val new: List<T>) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].toString() == new[newItemPosition].toString()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == new[newItemPosition]
}
