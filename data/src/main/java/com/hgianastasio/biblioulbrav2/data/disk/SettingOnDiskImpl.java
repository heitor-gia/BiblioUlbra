package com.hgianastasio.biblioulbrav2.data.disk;

import android.content.Context;

import com.hgianastasio.biblioulbrav2.core.settings.constants.DefaultSetting;
import com.hgianastasio.biblioulbrav2.data.disk.preferences.BasePreferences;
import com.hgianastasio.biblioulbrav2.data.base.setting.SettingOnDisk;
import com.hgianastasio.biblioulbrav2.data.models.settings.SettingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 03/06/17.
 */

public class SettingOnDiskImpl extends BasePreferences implements SettingOnDisk {
    private static final String KEY_SETTING = "SETTING_";

    @Inject
    public SettingOnDiskImpl(Context mContext) {
        super(mContext);
    }

    @Override
    public SettingEntity get(String key) {
        String value = getPreferences().getString(KEY_SETTING.concat(key),null);
        return new SettingEntity(key,value);
    }

    @Override
    public List<SettingEntity> getAll() {
        List<SettingEntity> settings = new ArrayList<>();
        for (DefaultSetting defaultSetting:DefaultSetting.values()) {
            if (isSeted(defaultSetting.getKey()))
                settings.add(get(defaultSetting.getKey()));
            else
                settings.add(new SettingEntity(defaultSetting.getKey(),defaultSetting.getDefaultValue()));
        }
        return settings;
    }

    @Override
    public boolean set(SettingEntity setting) {
        return getPreferences()
                .edit()
                .putString(
                        KEY_SETTING.concat(setting.getKey()),
                        setting.getValue())
                .commit();
    }

    @Override
    public boolean isSeted(String key) {
        return getPreferences().contains(KEY_SETTING.concat(key));
    }

    @Override
    public boolean remove(String key) {
        return getPreferences()
                .edit()
                .remove(key)
                .commit();
    }

    @Override
    public boolean clear() {
        return getPreferences()
                .edit()
                .clear()
                .commit();
    }
}
