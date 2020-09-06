package com.hgianastasio.biblioulbrav2.data.disk

import android.content.Context
import com.hgianastasio.biblioulbrav2.core.settings.constants.DefaultSetting
import com.hgianastasio.biblioulbrav2.data.base.setting.SettingOnDisk
import com.hgianastasio.biblioulbrav2.data.disk.preferences.BasePreferences
import com.hgianastasio.biblioulbrav2.data.models.settings.SettingEntity
import java.util.*
import javax.inject.Inject

/**
 * Created by heitor_12 on 03/06/17.
 */
class SettingOnDiskImpl @Inject constructor(mContext: Context) : BasePreferences(mContext), SettingOnDisk {
    override fun get(key: String?): SettingEntity? {
        if (key == null) return null
        val value = preferences.getString(KEY_SETTING + key, null)
        return SettingEntity(key, value)
    }

    override val all: List<SettingEntity>
        get() {
            val settings: MutableList<SettingEntity> = ArrayList()
            for (defaultSetting in DefaultSetting.values()) {
                if (isSet(defaultSetting.key)) get(defaultSetting.key)?.let(settings::add)
                else settings.add(SettingEntity(defaultSetting.key, defaultSetting.defaultValue))
            }
            return settings
        }

    override fun set(setting: SettingEntity?): Boolean {
        return preferences
                .edit()
                .putString("$KEY_SETTING${setting?.key}", setting?.value)
                .commit()
    }

    override fun isSet(key: String?): Boolean {
        return preferences.contains(KEY_SETTING + key)
    }

    override fun remove(key: String?): Boolean {
        return preferences
                .edit()
                .remove(key)
                .commit()
    }

    override fun clear(): Boolean {
        return preferences
                .edit()
                .clear()
                .commit()
    }

    companion object {
        private const val KEY_SETTING = "SETTING_"
    }
}