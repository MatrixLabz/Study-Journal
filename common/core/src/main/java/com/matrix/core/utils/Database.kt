/* Android with clean and multi module architecture */
package com.matrix.core.utils

import android.database.sqlite.SQLiteException
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Safely execute a database operation.
 */
suspend fun execute(block: suspend () -> Unit) {
    withContext(Dispatchers.IO) {
        try {
            block()
        } catch (e: SQLiteException) {
            e.localizedMessage?.let {
                Log.e("Database operation error", it)
            }
        }
    }
}
