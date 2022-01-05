/* Android with clean and multi module architecture */
package com.matrix.studyNote.ui.note

import android.view.LayoutInflater
import android.view.ViewGroup
import com.matrix.core.domain.models.Session
import com.matrix.core.ext.toFormattedTime
import com.matrix.core.ui.recyclerview.ListAdapter
import com.matrix.core.ui.recyclerview.getString
import com.matrix.studyNote.R
import com.matrix.studyNote.databinding.SessionListItemBinding

/**
 * ViewHolder for the sessions dynamic list.
 */
class ListViewHolder(private val binding: SessionListItemBinding) :
    ListAdapter.ViewHolder<Session>(binding) {

    companion object {
        operator fun invoke(parent: ViewGroup): ListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = SessionListItemBinding.inflate(inflater, parent, false)
            return ListViewHolder(binding)
        }
    }

    override fun bind(item: Session) {
        binding.sessionName.text = item.name
        binding.repetitions.text = getString(R.string.session_list_item_text_1, item.repetitions)
        binding.breakDuration.text = getString(
            R.string.session_list_item_text_2,
            item.breakDuration.toFormattedTime()
        )
    }
}
