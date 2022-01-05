/* Android with clean and multi module architecture */
package com.matrix.main.ext

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

/**
 * Opens a link in a browser.
 */
internal fun Fragment.openLink(uri: String) =
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
