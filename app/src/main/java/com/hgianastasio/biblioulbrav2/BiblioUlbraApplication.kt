package com.hgianastasio.biblioulbrav2

import android.app.Application
import com.hgianastasio.biblioulbrav2.di.DaggerApplicationInjector

/**
 * Created by heitorgianastasio on 4/29/17.
 */
class BiblioUlbraApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DaggerApplicationInjector.Companion.init(this)
    }
}