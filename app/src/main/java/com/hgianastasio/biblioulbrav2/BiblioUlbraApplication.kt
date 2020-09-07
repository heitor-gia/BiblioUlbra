package com.hgianastasio.biblioulbrav2

import android.app.Application
import com.hgianastasio.biblioulbrav2.di.modules
import com.hgianastasio.biblioulbrav2.di.modules.ApplicationModuleProvider
import com.hgianastasio.biblioulbrav2.di.modules.DataModuleProvider
import com.hgianastasio.biblioulbrav2.di.modules.PresentationModuleProvider
import com.hgianastasio.biblioulbrav2.di.modules.RetrofitModuleProvider
import com.hgianastasio.biblioulbrav2.di.modules.UseCaseModuleProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by heitorgianastasio on 4/29/17.
 */
class BiblioUlbraApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BiblioUlbraApplication)
            modules(
                    ApplicationModuleProvider,
                    DataModuleProvider,
                    PresentationModuleProvider,
                    RetrofitModuleProvider,
                    UseCaseModuleProvider
            )
        }
    }
}