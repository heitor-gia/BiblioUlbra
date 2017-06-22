package com.hgianastasio.biblioulbrav2.core.settings.interactors;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.settings.Setting;
import com.hgianastasio.biblioulbrav2.core.settings.constants.DefaultSetting;
import com.hgianastasio.biblioulbrav2.core.settings.exceptions.SettingNotFoundException;
import com.hgianastasio.biblioulbrav2.core.settings.repository.SettingRepository;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 03/06/17.
 */

public class SetSettingToDefault extends UseCase<Setting,String> {
    private SettingRepository repository;

    @Inject
    public SetSettingToDefault(ThreadPoolExecutor executor, SettingRepository repository) {
        super(executor);
        this.repository = repository;
    }

    @Override
    protected void process(String key, OnResultListener<Setting> resultListener, OnErrorListener errorListener) {
        try {
            repository.remove(key);
        }catch (Exception e){
            errorListener.onError(e);
        }
    }

}
