package com.hgianastasio.biblioulbrav2.core.settings.constants;

/**
 * Created by heitor_12 on 06/06/17.
 */

public enum DefaultSetting {
    loansNotification("loansNotification","1", "Notificação de empréstimos", "dia(s)");


    private String key;
    private String defaultValue;
    private String name;
    private String unit;


    DefaultSetting(String key, String defaultValue, String name, String unit) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.name = name;
        this.unit = unit;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getUnit() {
        return unit;
    }

    public static DefaultSetting findByKey(String key){
        for(DefaultSetting setting: values())
            if (setting.key.equals(key))
                return setting;
        return null;
    }

    public String getName() {
        return name;
    }
}
