package me.alberto.cowrywiseconveter.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.alberto.cowrywiseconveter.di.module.DataModule
import me.alberto.cowrywiseconveter.di.module.RemoteModule
import me.alberto.cowrywiseconveter.di.module.ViewModelModule
import me.alberto.cowrywiseconveter.screens.home.view.HomeFragment

@Component(modules = [ViewModelModule::class, DataModule::class, RemoteModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: HomeFragment)
}