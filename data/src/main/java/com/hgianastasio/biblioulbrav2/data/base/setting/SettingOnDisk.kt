package com.hgianastasio.biblioulbrav2.data.base.setting

import com.hgianastasio.biblioulbrav2.data.models.settings.SettingEntity

/**
 * Created by heitor_12 on 03/06/17.
 */
interface SettingOnDisk {
    operator fun get(key: String?): SettingEntity?
    val all: List<SettingEntity>
    fun set(setting: SettingEntity?): Boolean
    fun isSet(key: String?): Boolean
    fun remove(key: String?): Boolean
    fun clear(): Boolean
}