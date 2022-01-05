/* Android with clean and multi module architecture */
package com.matrix.core.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.matrix.core.ui.StateOwner
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Observes incoming states when the lifecycle is at least STARTED.
 */
fun <T> Fragment.onStates(stateOwner: StateOwner<T>, block: (T) -> Unit) {
    lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            stateOwner.state.collectLatest { block(it) }
        }
    }
}

/**
 * Runs the block of code in a coroutine when the lifecycle is at least STARTED.
 */
fun Fragment.launchAfterStarted(block: suspend () -> Unit) {
    lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}

/**
 * Set the action bar's title.
 *
 * @param title Title to set
 */
fun Fragment.setActionBarTitle(title: String?) {
    (requireActivity() as AppCompatActivity).supportActionBar?.title = title
}
