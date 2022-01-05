/* Android with clean and multi module architecture */
package com.matrix.main.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import com.matrix.core.domain.models.StudyNote
import com.matrix.core.ui.recyclerview.ListAdapter
import com.matrix.main.databinding.HistoryListItemBinding

/**
 * ViewHolder for the history dynamic list.
 */
class ListViewHolder(
    private val binding: HistoryListItemBinding,
    private val onItemClick: (StudyNote) -> Unit
) : ListAdapter.ViewHolder<StudyNote>(binding) {

    companion object {
        operator fun invoke(parent: ViewGroup, onItemClick: (StudyNote) -> Unit) =
            ListViewHolder(
                HistoryListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), onItemClick
            )
    }

    override fun bind(item: StudyNote) {
        binding.noteTitle.text = item.title
        binding.noteCreatedAt.text = item.createdAt
        itemView.setOnClickListener { onItemClick(item) }
    }
}
