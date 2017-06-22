package com.hgianastasio.biblioulbrav2.core.settings;

import com.hgianastasio.biblioulbrav2.core.settings.constants.DefaultSetting;

/**
 * Created by heitor_12 on 03/06/17.
 */

public class Setting {
    private String key;
    private String value;
    private String name;
    private String unit;

    public Setting() {}

    public Setting(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Setting(String key, String value, String name, String unit) {
        this(key,value);
        this.name = name;
        this.unit = unit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Setting fromDefaultSetting(DefaultSetting defaultSetting){
        return new Setting(defaultSetting.getKey(),defaultSetting.getDefaultValue());
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
