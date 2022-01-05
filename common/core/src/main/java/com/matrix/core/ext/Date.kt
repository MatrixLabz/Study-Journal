package com.matrix.core.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * Formats a Date into a string using the given pattern.
 *
 * @param pattern default pattern (two-digit month, two-digit day, separator, four-digit year).
 */
fun Date.format(pattern: String = "dd.MM.yyyy"): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)
