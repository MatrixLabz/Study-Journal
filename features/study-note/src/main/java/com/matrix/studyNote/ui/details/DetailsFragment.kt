/* Android with clean and multi module architecture */
package com.matrix.studyNote.ui.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.matrix.core.ext.hideKeyboard
import com.matrix.core.utils.navigation.navigate
import com.matrix.studyNote.R
import com.matrix.studyNote.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment @Inject constructor() : Fragment(R.layout.fragment_details) {
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()
    private val binding: FragmentDetailsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameTextField.setText(args.properties.title)
    }

    override fun onPause() {
        super.onPause()
        view?.hideKeyboard()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.actionDone -> {
            val props = args.properties.copy(title = binding.nameTextField.text.toString())
            viewModel.renameWorkout(props.id, props.title)
            navigate(DetailsFragmentDirections.actionDetailsFragmentToNoteFragment(props))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
