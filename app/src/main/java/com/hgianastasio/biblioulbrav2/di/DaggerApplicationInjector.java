package com.hgianastasio.biblioulbrav2.di;

import android.app.Application;

import com.hgianastasio.biblioulbrav2.di.components.ApplicationComponent;
import com.hgianastasio.biblioulbrav2.di.components.DaggerApplicationComponent;
import com.hgianastasio.biblioulbrav2.di.modules.ApplicationModule;


/**
 * Created by heitorgianastasio on 4/23/17.
 */

public class DaggerApplicationInjector {

    private static DaggerApplicationInjector instance = null;
    private ApplicationComponent component;

    private DaggerApplicationInjector(Application app){
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(app))
                .build();
    }
    static public void init(Application app){
        if(instance==null)
            instance = new DaggerApplicationInjector(app);
    }

    static public ApplicationComponent get(){
        if(instance==null)
            throw new RuntimeException("the ApplicationComponent is null");
        return instance.component;
    }


}
