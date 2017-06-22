package com.hgianastasio.biblioulbrav2.di.modules;

import android.app.Activity;

import com.hgianastasio.biblioulbrav2.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by heitor_12 on 04/05/17.
 */
@Module
public class ActivityModule {
    Activity activity;

    public ActivityModule(Activity activity) {this.activity = activity;}

    @Provides @PerActivity
    Activity provideActivity(){return activity;}


}
