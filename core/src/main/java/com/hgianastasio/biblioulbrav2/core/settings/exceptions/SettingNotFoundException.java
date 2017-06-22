package com.hgianastasio.biblioulbrav2.core.settings.exceptions;

/**
 * Created by heitor_12 on 06/06/17.
 */

public class SettingNotFoundException extends RuntimeException {
    private String key;

    public SettingNotFoundException(String key) {
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "A configuração ".concat(key).concat(" não foi encontrada.");
    }
}
