/* Android with clean and multi module architecture */
package com.matrix.core.domain.models

data class Session(
    val id: Int,
    val name: String,
    val repetitions: String,
    val breakDuration: Long
)
