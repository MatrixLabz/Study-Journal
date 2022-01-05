/* Android with clean and multi module architecture */
package com.matrix.studyNote.ext

import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

/**
 * Returns or set the text that TextView is displaying.
 */
var TextInputLayout.text: String
    get() = editText?.text.toString()
    set(value) {
        editText?.setText(value)
    }

/**
 * Invoke an action the text is changing.
 */
fun TextInputLayout.doOnTextChanged(action: () -> Unit) =
    editText?.doOnTextChanged { _, _, _, _ -> action() }
