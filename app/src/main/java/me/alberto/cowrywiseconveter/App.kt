package me.alberto.cowrywiseconveter

import android.app.Application
import me.alberto.cowrywiseconveter.di.component.AppComponent
import me.alberto.cowrywiseconveter.di.component.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}