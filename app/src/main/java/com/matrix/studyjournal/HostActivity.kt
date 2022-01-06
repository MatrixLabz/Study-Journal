/* Android with clean and multi module architecture */

package com.matrix.studyjournal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.matrix.core.di.setupFragmentFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : AppCompatActivity(R.layout.activity_host) {


    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.Theme_StudyJournal)
        setupFragmentFactory()
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupNavController()
    }

    private fun setupNavController() {
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(
            findNavController(),
            AppBarConfiguration.Builder(R.id.splashFragment, R.id.mainFragment, R.id.timerFragment).build()
        )
    }

    private fun findNavController() = (supportFragmentManager
        .findFragmentById(R.id.navHostFragment) as NavHostFragment)
        .navController
}
