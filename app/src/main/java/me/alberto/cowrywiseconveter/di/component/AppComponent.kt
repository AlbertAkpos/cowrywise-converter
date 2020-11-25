package me.alberto.cowrywiseconveter.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.alberto.cowrywiseconveter.di.module.DataModule
import me.alberto.cowrywiseconveter.di.module.LocalModule
import me.alberto.cowrywiseconveter.di.module.RemoteModule
import me.alberto.cowrywiseconveter.di.module.ViewModelModule
import me.alberto.cowrywiseconveter.di.scopes.AppScope
import me.alberto.cowrywiseconveter.screens.history.view.ChartFragment
import me.alberto.cowrywiseconveter.screens.home.view.HomeFragment
import javax.inject.Singleton

@AppScope
@Component(modules = [ViewModelModule::class, DataModule::class, RemoteModule::class, LocalModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: ChartFragment)
}