/* Android with clean and multi module architecture */
package com.matrix.main.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.matrix.core.utils.navigation.GlobalDirections
import com.matrix.core.utils.navigation.GlobalNavHost
import com.matrix.main.R
import com.matrix.main.databinding.FragmentMainBinding
import com.matrix.main.ui.history.HistoryFragment
import com.matrix.main.ui.pager.ViewPagerAdapter
import com.matrix.main.ui.settings.SettingsFragment
import com.matrix.main.ui.splashscreen.SplashFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.appcompat.app.AppCompatActivity




@AndroidEntryPoint
class MainFragment @Inject constructor(
    private val directions: GlobalDirections,
    private val host: GlobalNavHost,
    private val versionName: String
) : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val tabIcons = arrayOf(
        R.drawable.ic_baseline_bar_chart_24, R.drawable.ic_baseline_settings_24
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPagerAdapter(listOf(HistoryFragment(directions, host), SettingsFragment(versionName)))
        setupTabLayoutWithViewPager()
    }

    private fun setViewPagerAdapter(fragments: List<Fragment>) {
        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle, fragments)
    }

    private fun setupTabLayoutWithViewPager() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.icon = AppCompatResources.getDrawable(requireContext(), tabIcons[position])
        }.attach()
    }

    override fun onResume() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        super.onResume()
    }
}
