package com.hgianastasio.biblioulbrav2.core.settings.repository;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.settings.Setting;

import java.io.IOException;
import java.util.List;

/**
 * Created by heitor_12 on 03/06/17.
 */

public interface SettingRepository {
    void get(String key, OnResultListener<Setting> resultListener) throws IOException;
    void getAll(OnResultListener<List<Setting>> resultListener) throws IOException;
    boolean set(Setting setting);
    boolean isSeted(String key);
    boolean remove(String key);
}
