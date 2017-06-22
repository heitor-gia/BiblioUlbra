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

public class GetSetting extends UseCase<Setting,String> {
    private SettingRepository repository;

    @Inject
    public GetSetting(ThreadPoolExecutor executor, SettingRepository repository) {
        super(executor);
        this.repository = repository;
    }

    @Override
    protected void process(String key, OnResultListener<Setting> resultListener, OnErrorListener errorListener) {
        try {
            if (repository.isSeted(key))
                repository.get(key,resultListener);
            else
                getDefaultSetting(key,resultListener);
        }catch (Exception e){
            errorListener.onError(e);
        }
    }

    private void getDefaultSetting(String key, OnResultListener<Setting> resultListener) throws Exception{
        DefaultSetting defaultSetting = DefaultSetting.findByKey(key);
        if(defaultSetting!=null)
            resultListener.onResult(Setting.fromDefaultSetting(defaultSetting));
        else
            throw new SettingNotFoundException(key);
    }
}
