/* Android with clean and multi module architecture */
package com.matrix.core.ext

import java.util.Locale

/**
 * Returns a copy of this string having its first letter uppercase.
 */
val String.capitalized
    get() = replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
