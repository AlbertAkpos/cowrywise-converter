package me.alberto.cowrywiseconveter.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.alberto.cowrywiseconveter.di.viewmodel.ViewModelFactory
import me.alberto.cowrywiseconveter.di.viewmodel.ViewModelKey
import me.alberto.cowrywiseconveter.screens.home.viewmodel.HomeViewModel

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}