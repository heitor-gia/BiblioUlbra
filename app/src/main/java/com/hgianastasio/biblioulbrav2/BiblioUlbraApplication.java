package com.hgianastasio.biblioulbrav2;

import android.app.Application;

import com.hgianastasio.biblioulbrav2.di.DaggerApplicationInjector;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

public class BiblioUlbraApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationInjector.init(this);
    }
}
