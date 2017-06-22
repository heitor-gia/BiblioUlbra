package com.hgianastasio.biblioulbrav2.data.models.settings;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.settings.Setting;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 04/06/17.
 */

public class SettingEntityMapper extends Mapper<Setting,SettingEntity> {
    @Inject public SettingEntityMapper() {}

    @Override
    public SettingEntity transform(Setting input) {
        return new SettingEntity(input.getKey(), input.getValue());
    }

    @Override
    public Setting transformBack(SettingEntity input) {
        return new Setting(input.getKey(),input.getValue());
    }
}
