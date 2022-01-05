/* Android with clean and multi module architecture */
package com.matrix.main.ui.history

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.matrix.core.domain.models.StudyNote
import com.matrix.core.ext.capitalized
import com.matrix.core.ext.launchAfterStarted
import com.matrix.core.ext.onStates
import com.matrix.core.ui.recyclerview.ITEM_IS_AT_BOTTOM
import com.matrix.core.ui.recyclerview.ITEM_IS_ON_TOP
import com.matrix.core.utils.navigation.GlobalDirections
import com.matrix.core.utils.navigation.GlobalNavHost
import com.matrix.core.utils.navigation.models.toProperties
import com.matrix.core.utils.navigation.navigate
import com.matrix.core.ui.recyclerview.decorations.SpacingItemDecoration
import com.matrix.core.ui.recyclerview.findItemPosition
import com.matrix.core.ui.recyclerview.listAdapterOf
import com.matrix.core.ui.recyclerview.setOnSwipedListener
import com.matrix.core.utils.showUndoMessage
import com.matrix.main.R
import com.matrix.main.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment(
    private val directions: GlobalDirections,
    private val host: GlobalNavHost
) : Fragment(R.layout.fragment_history) {
    private val viewModel: HistoryViewModel by viewModels()
    private val binding: FragmentHistoryBinding by viewBinding()

    private val adapter = listAdapterOf { ListViewHolder(it, ::onListItemClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHistoryList(requireContext())
        renderHistoryListStates()
        binding.newWorkoutNote.setOnClickListener(::onClick)
    }

    private fun setupHistoryList(context: Context) {
        binding.historyList.addItemDecoration(SpacingItemDecoration(context))
        adapter.subscribeToDataChanges(::onDataChanged)
        binding.historyList.adapter = adapter
        onStates(viewModel, adapter::setState)
        binding.historyList.setOnSwipedListener(context, ::onSwiped)
    }

    private fun renderHistoryListStates() = launchAfterStarted {
        adapter.state.collectLatest {
            binding.progressIndicator.isVisible = it.isLoading
            binding.historyListHint.isVisible = it.isEmpty
            binding.historyList.isInvisible = it.isLoading || it.isEmpty
            binding.newWorkoutNote.isVisible = it.isEmpty || !it.isLoading
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onClick(view: View) = lifecycleScope.launch {
        adapter.unsubscribeFromDataChanges()
        val res = viewModel.addNewWorkoutNote()
        navigate(directions.fromMainToStudyNote(res.toProperties()), host)
    }

    private fun onSwiped(position: Int) = with(adapter.getItemAt(position)) {
        viewModel.deleteWorkoutNote(this)
        showUndoMessage(requireView(), getString(R.string.undo_message, title.capitalized)) {
            viewModel.restoreWorkoutNote(this)
        }
    }

    private fun onListItemClick(studyNote: StudyNote) {
        adapter.unsubscribeFromDataChanges()
        navigate(directions.fromMainToStudyNote(studyNote.toProperties()), host)
    }

    private fun onDataChanged(itemPosition: Int) = with(binding.historyList) {
        when (findItemPosition(itemPosition)) {
            ITEM_IS_ON_TOP, ITEM_IS_AT_BOTTOM -> scrollToPosition(itemPosition)
        }
    }
}
