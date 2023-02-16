package ru.cft.shift2023winter

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.cft.shift2023winter.di.provideDataModule
import ru.cft.shift2023winter.di.provideDomainModule
import ru.cft.shift2023winter.di.provideNetworkModule
import ru.cft.shift2023winter.di.providePresentationModule

class ShiftApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ShiftApplication)
            modules(
                provideNetworkModule(),
                provideDataModule(),
                provideDomainModule(),
                providePresentationModule(),
            )
        }
    }
}