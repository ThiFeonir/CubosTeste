package com.example.cubosteste.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.cubosteste.injection.modules.appModule
import com.example.cubosteste.injection.modules.dataModule
import com.example.cubosteste.injection.modules.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CubosApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        startKoin {
            androidContext(this@CubosApplication)
            modules(listOf(
                appModule,
                dataModule,
                presentationModule))
        }
    }
}