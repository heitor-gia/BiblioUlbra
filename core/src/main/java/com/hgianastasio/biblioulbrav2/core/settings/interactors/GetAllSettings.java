package com.hgianastasio.biblioulbrav2.core.settings.interactors;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.settings.Setting;
import com.hgianastasio.biblioulbrav2.core.settings.constants.DefaultSetting;
import com.hgianastasio.biblioulbrav2.core.settings.exceptions.SettingNotFoundException;
import com.hgianastasio.biblioulbrav2.core.settings.repository.SettingRepository;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 03/06/17.
 */

public class GetAllSettings extends UseCase<List<Setting>,Void> {
    private SettingRepository repository;

    @Inject
    public GetAllSettings(ThreadPoolExecutor executor, SettingRepository repository) {
        super(executor);
        this.repository = repository;
    }

    @Override
    protected void process(Void params, OnResultListener<List<Setting>> resultListener, OnErrorListener errorListener) {
        try {
            repository.getAll(resultListener);
        }catch (Exception e){
            errorListener.onError(e);
        }
    }
}
