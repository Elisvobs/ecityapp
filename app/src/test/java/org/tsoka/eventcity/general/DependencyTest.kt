package org.tsoka.eventcity.general

import android.app.Application
import org.tsoka.eventcity.general.di.apiModule
import org.tsoka.eventcity.general.di.commonModule
import org.tsoka.eventcity.general.di.databaseModule
import org.tsoka.eventcity.general.di.networkModule
import org.tsoka.eventcity.general.di.viewModelModule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito.mock

class DependencyTest : KoinTest {
    @Test
    fun testDependencies() {
        koinApplication {
            androidContext(mock(Application::class.java))
            modules(listOf(commonModule, apiModule, databaseModule, networkModule, viewModelModule))
        }.checkModules()
    }
}
