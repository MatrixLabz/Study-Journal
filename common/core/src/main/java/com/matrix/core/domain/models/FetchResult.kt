/* Android with clean and multi module architecture */
package com.matrix.core.domain.models

/**
 * Final status after a successful fetch from the database.
 */
sealed class FetchResult<out T> {
    object Loading : FetchResult<Nothing>()
    object Empty : FetchResult<Nothing>()
    data class Result<T>(val value: List<T>) : FetchResult<T>()
}
