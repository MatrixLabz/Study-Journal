/* Android with clean and multi module architecture */
package com.matrix.core.ui.models

/**
 * Types of load states of the list.
 */
data class ListState<T>(
    val isLoading: Boolean = true,
    val isEmpty: Boolean = false,
    val items: List<T> = emptyList()
)
