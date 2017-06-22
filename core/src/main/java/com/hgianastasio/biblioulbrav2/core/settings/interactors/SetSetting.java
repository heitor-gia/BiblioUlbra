package com.hgianastasio.biblioulbrav2.core.settings.interactors;

import com.hgianastasio.biblioulbrav2.core.base.UseCase;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.settings.Setting;
import com.hgianastasio.biblioulbrav2.core.settings.repository.SettingRepository;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 03/06/17.
 */

public class SetSetting extends UseCase<Void,Setting> {
    SettingRepository repository;

    @Inject
    public SetSetting(ThreadPoolExecutor executor, SettingRepository repository) {
        super(executor);
        this.repository = repository;
    }


    @Override
    protected void process(Setting params, OnResultListener<Void> resultListener, OnErrorListener errorListener) {
        try {
            repository.set(params);
            resultListener.onResult(null);
        } catch (Exception e) {
            errorListener.onError(new RuntimeException("Não foi possível atualizar a configuração",e));
        }
    }
}