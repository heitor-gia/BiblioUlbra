package com.hgianastasio.biblioulbrav2.data.base.setting;

import com.hgianastasio.biblioulbrav2.data.models.settings.SettingEntity;

import java.util.List;

/**
 * Created by heitor_12 on 03/06/17.
 */

public interface SettingOnDisk {
    SettingEntity get(String key);
    List<SettingEntity> getAll();
    boolean set(SettingEntity setting);
    boolean isSeted(String key);
    boolean remove(String key);
    boolean clear();


}
