/* Android with clean and multi module architecture */
package com.matrix.core.ext

import java.util.concurrent.TimeUnit

/**
 * Converts a Long to a formatted string representation.
 */
fun Long.toFormattedTime() =
    String.format("%02d:%02d", this.toMinutes(), this.toSeconds())

/**
 * Converts a Long to milliseconds.
 */
fun Long.toMilliseconds() = TimeUnit.SECONDS.toMillis(this)

/**
 * Converts a Long to minutes.
 */
private fun Long.toMinutes() = TimeUnit.MILLISECONDS.toMinutes(this) -
        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(this))

/**
 * Converts a Long to seconds.
 */
private fun Long.toSeconds() = TimeUnit.MILLISECONDS.toSeconds(this) -
        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))
