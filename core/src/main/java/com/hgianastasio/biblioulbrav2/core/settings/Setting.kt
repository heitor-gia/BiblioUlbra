package com.hgianastasio.biblioulbrav2.core.settings

import com.hgianastasio.biblioulbrav2.core.settings.constants.DefaultSetting

/**
 * Created by heitor_12 on 03/06/17.
 */
class Setting {
    var key: String? = null
    var value: String? = null
    var name: String? = null
    var unit: String? = null

    constructor() {}
    constructor(key: String?, value: String?) {
        this.key = key
        this.value = value
    }

    constructor(key: String?, value: String?, name: String?, unit: String?) : this(key, value) {
        this.name = name
        this.unit = unit
    }

    companion object {
        fun fromDefaultSetting(defaultSetting: DefaultSetting): Setting {
            return Setting(defaultSetting.key, defaultSetting.defaultValue)
        }
    }
}