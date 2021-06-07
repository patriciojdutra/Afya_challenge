package br.com.dutra.patricio.tvmaze

import android.app.Application
import br.com.dutra.patricio.tvmaze.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(mainModule)
        }
    }
}