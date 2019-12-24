package org.tsoka.eventcity.general

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import org.tsoka.eventcity.general.di.apiModule
import org.tsoka.eventcity.general.di.commonModule
import org.tsoka.eventcity.general.di.databaseModule
import org.tsoka.eventcity.general.di.networkModule
import org.tsoka.eventcity.general.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class OpenEventGeneral : MultiDexApplication() {

    companion object {
        @JvmStatic
        var appContext: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidLogger()
            androidContext(this@OpenEventGeneral)
            modules(listOf(
                commonModule,
                apiModule,
                viewModelModule,
                networkModule,
                databaseModule
            ))
        }
        Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(applicationContext)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
