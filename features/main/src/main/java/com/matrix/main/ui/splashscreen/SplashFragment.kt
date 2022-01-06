package com.matrix.main.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.matrix.core.utils.navigation.GlobalDirections
import com.matrix.core.utils.navigation.GlobalNavHost
import com.matrix.core.utils.navigation.models.toProperties
import com.matrix.core.utils.navigation.navigate
import com.matrix.main.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.appcompat.app.AppCompatActivity



@AndroidEntryPoint
class SplashFragment @Inject constructor(
    private val directions: GlobalDirections,
    private val host: GlobalNavHost
): Fragment(R.layout.fragment_splash) {

    private val fragmentScoped = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentScoped.launch {
            delay(500)
            navigate(directions.fromSplashToMain(), host)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        super.onResume()
    }

}