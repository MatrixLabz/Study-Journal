/* Android with clean and multi module architecture */
package com.matrix.main.ui.settings

import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.matrix.main.BuildConfig
import com.matrix.main.R
import com.matrix.main.ext.openLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment(private val versionName: String) : PreferenceFragmentCompat(),
    Preference.OnPreferenceClickListener {

    private val versionPreference: Preference? by lazy {
        findPreference(getString(R.string.version_key))
    }

    private val rateAppPreference: Preference? by lazy {
        findPreference(getString(R.string.rate_app_key))
    }

    private val githubLinkPreference: Preference? by lazy {
        findPreference(getString(R.string.github_link))
    }

    private val privacyPolicyPreference: Preference? by lazy {
        findPreference(getString(R.string.privacy_policy_key))
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        versionPreference?.let { it.summary = versionName }
        versionPreference?.onPreferenceClickListener = this
        rateAppPreference?.onPreferenceClickListener = this
        githubLinkPreference?.onPreferenceClickListener = this
        privacyPolicyPreference?.onPreferenceClickListener = this
    }

    override fun onPreferenceClick(preference: Preference) = when (preference.key) {
        getString(R.string.rate_app_key) -> {
//            openLink(BuildConfig.GOOGLE_PLAY_URL)
            Toast.makeText(activity, "Not yet uploaded to playstore!", Toast.LENGTH_SHORT).show()
            true
        }
//        getString(R.string.privacy_policy_key) -> {
////            openLink(BuildConfig.PRIVACY_POLICY_URL)
//            Toast.makeText(activity, "URL will be uploading soon!", Toast.LENGTH_LONG).show()
//            true
//        }

        getString(R.string.github_link) -> {
            openLink(BuildConfig.GITHUB_URL)
            true
        }

        getString(R.string.version_key) -> {
            Toast.makeText(requireContext(), versionName, Toast.LENGTH_SHORT).show()
            true
        }

        else -> false
    }
}
