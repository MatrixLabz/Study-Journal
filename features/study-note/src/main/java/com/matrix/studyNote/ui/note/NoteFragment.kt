
package com.matrix.studyNote.ui.note

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.matrix.core.ext.capitalized
import com.matrix.core.ext.launchAfterStarted
import com.matrix.core.ext.onStates
import com.matrix.core.ext.setActionBarTitle
import com.matrix.core.ui.recyclerview.*
import com.matrix.core.ui.recyclerview.decorations.SpacingItemDecoration
import com.matrix.core.ui.recyclerview.decorations.defaultDividerItemDecoration
import com.matrix.core.utils.navigation.navigate
import com.matrix.core.utils.showUndoMessage
import com.matrix.studyNote.R
import com.matrix.studyNote.databinding.FragmentNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class NoteFragment @Inject constructor() : Fragment(R.layout.fragment_note) {
    private val args: NoteFragmentArgs by navArgs()
    private val binding: FragmentNoteBinding by viewBinding()
    private val viewModel: NoteViewModel by viewModels()

    private val adapter = listAdapterOf { ListViewHolder(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle(args.properties.title)
        setupSessionList(requireContext())
        renderSessionListState()
        binding.newWorkoutNote.setOnClickListener(::onClick)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.actionDetails -> {
            adapter.unsubscribeFromDataChanges()
            navigate(NoteFragmentDirections.actionNoteFragmentToDetailsFragment(args.properties))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupSessionList(context: Context) {
        binding.sessionList.addItemDecoration(defaultDividerItemDecoration(context))
        binding.sessionList.addItemDecoration(SpacingItemDecoration(context, space = 16))
        adapter.subscribeToDataChanges(::onDataChanged)
        binding.sessionList.adapter = adapter
        onStates(viewModel, adapter::setState)
        binding.sessionList.setOnSwipedListener(context, ::onSwiped)
    }

    private fun renderSessionListState() = launchAfterStarted {
        adapter.state.collectLatest {
            binding.progressIndicator.isVisible = it.isLoading
            binding.sessionListHint.isVisible = it.isEmpty
            binding.sessionList.isInvisible = it.isLoading || it.isEmpty
            binding.newWorkoutNote.isVisible = it.isEmpty || !it.isLoading
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onClick(view: View) {
        adapter.unsubscribeFromDataChanges()
        navigate(NoteFragmentDirections.actionNoteFragmentToSessionFragment(args.properties))
    }

    private fun onDataChanged(itemPosition: Int) = with(binding.sessionList) {
        when (findItemPosition(itemPosition)) {
            ITEM_IS_ON_TOP, ITEM_IS_AT_BOTTOM -> scrollToPosition(itemPosition)
        }
    }

    private fun onSwiped(position: Int) = with(adapter.getItemAt(position)) {
        viewModel.deleteSession(args.properties.id, this)
        showUndoMessage(requireView(), getString(R.string.undo_message, name.capitalized)) {
            viewModel.restoreSession(args.properties.id, this)
        }
    }
}
