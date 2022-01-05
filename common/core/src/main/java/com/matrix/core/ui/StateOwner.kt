/* Android with clean and multi module architecture */
package com.matrix.core.ui

import kotlinx.coroutines.flow.StateFlow

/**
 * Interface that indicates that the class has a UI state.
 */
interface StateOwner<T> {
    val state: StateFlow<T>
}
