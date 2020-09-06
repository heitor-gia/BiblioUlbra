package com.hgianastasio.biblioulbrav2.di

import android.app.Application
import com.hgianastasio.biblioulbrav2.di.components.ApplicationComponent
import com.hgianastasio.biblioulbrav2.di.components.DaggerApplicationComponent
import com.hgianastasio.biblioulbrav2.di.modules.ApplicationModule

/**
 * Created by heitorgianastasio on 4/23/17.
 */
class DaggerApplicationInjector private constructor(app: Application) {
    private val component: ApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(app))
            .build()

    companion object {
        private var instance: DaggerApplicationInjector? = null
        fun init(app: Application) {
            if (instance == null) instance = DaggerApplicationInjector(app)
        }

        fun get(): ApplicationComponent {
            if (instance != null) return instance!!.component
            else throw RuntimeException("the ApplicationComponent is null")
        }
    }

}