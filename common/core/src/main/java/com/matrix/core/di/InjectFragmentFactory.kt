
package com.matrix.core.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Provider

/**
 * Entry point for Hilt's Fragment factory.
 */
@EntryPoint
@InstallIn(ActivityComponent::class)
interface InjectFragmentFactoryEntryPoint {
    fun getFragmentFactory(): InjectFragmentFactory
}

/**
 * Hilt implementation of the Fragment factory interface.
 */
class InjectFragmentFactory @Inject constructor(
    private val providers: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator = providers[fragmentClass] ?: providers.entries.firstOrNull {
            fragmentClass.isAssignableFrom(it.key)
        }?.value
        return creator?.get() ?: super.instantiate(classLoader, className)
    }
}

/**
 * Sets up an Activity for use with a Fragment factory.
 */
fun AppCompatActivity.setupFragmentFactory() {
    val entryPoint = EntryPointAccessors.fromActivity(
        this,
        InjectFragmentFactoryEntryPoint::class.java
    )
    supportFragmentManager.fragmentFactory = entryPoint.getFragmentFactory()
}
