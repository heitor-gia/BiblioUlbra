package com.hgianastasio.biblioulbrav2.data.models.settings;

/**
 * Created by heitor_12 on 04/06/17.
 */

public class SettingEntity {
    String key;
    String value;

    public SettingEntity(String key, String value) {
        this.key = key;
        this.value = value;
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
}
