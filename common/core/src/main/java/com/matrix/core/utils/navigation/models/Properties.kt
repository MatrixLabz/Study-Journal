/* Android with clean and multi module architecture */
package com.matrix.core.utils.navigation.models

import android.os.Parcelable
import com.matrix.core.domain.models.StudyNote
import kotlinx.parcelize.Parcelize

/**
 * Parcelable data class for Safe Args.
 */
@Parcelize
data class Properties(
    val id: Int,
    val title: String,
    val breakDuration: Long = 0
) : Parcelable

fun StudyNote.toProperties() = Properties(id, title)
