package com.hgianastasio.biblioulbrav2.di.modules

import android.app.Activity
import com.hgianastasio.biblioulbrav2.di.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by heitor_12 on 04/05/17.
 */
@Module
class ActivityModule(var activity: Activity) {
    @Provides
    @PerActivity
    fun provideActivity(): Activity {
        return activity
    }

}