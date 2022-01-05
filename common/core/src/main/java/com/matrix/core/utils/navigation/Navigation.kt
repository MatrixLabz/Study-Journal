/* Android with clean and multi module architecture */
package com.matrix.core.utils.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

/**
 * Navigate via the given NavDirections in inner or global navigation host.
 */
fun Fragment.navigate(direction: NavDirections, host: GlobalNavHost? = null) {
    when (host) {
        null -> findNavController()
        else -> Navigation.findNavController(requireActivity(), host.id)
    }.also { it.navigate(direction) }
}
