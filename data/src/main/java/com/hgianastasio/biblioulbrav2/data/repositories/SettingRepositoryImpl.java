package com.hgianastasio.biblioulbrav2.data.repositories;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.settings.Setting;
import com.hgianastasio.biblioulbrav2.core.settings.repository.SettingRepository;
import com.hgianastasio.biblioulbrav2.data.base.setting.SettingOnDisk;
import com.hgianastasio.biblioulbrav2.data.models.settings.SettingEntityMapper;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 03/06/17.
 */

public class SettingRepositoryImpl implements SettingRepository {

    private SettingOnDisk disk;
    private SettingEntityMapper mapper;

    @Inject
    public SettingRepositoryImpl(SettingOnDisk disk, SettingEntityMapper mapper) {
        this.disk = disk;
        this.mapper = mapper;
    }

    @Override
    public void get(String key, OnResultListener<Setting> resultListener) throws IOException {
        resultListener.onResult(mapper.transformBack(disk.get(key)));
    }

    @Override
    public void getAll(OnResultListener<List<Setting>> resultListener) throws IOException {
        resultListener.onResult(mapper.tansformBack(disk.getAll()));
    }

    @Override
    public boolean set(Setting setting) {
        return disk.set(mapper.transform(setting));
    }

    @Override
    public boolean isSeted(String key) {
        return disk.isSeted(key);
    }

    @Override
    public boolean remove(String key) {
        return disk.remove(key);
    }
}
