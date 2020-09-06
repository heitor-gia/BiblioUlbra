package com.hgianastasio.biblioulbrav2.core.settings.repository

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.settings.Setting
import java.io.IOException

/**
 * Created by heitor_12 on 03/06/17.
 */
interface SettingRepository {
    @Throws(IOException::class)
    operator fun get(key: String?, resultListener: OnResultListener<Setting?>)

    @Throws(IOException::class)
    fun getAll(resultListener: OnResultListener<List<Setting>>)
    fun set(setting: Setting?): Boolean
    fun isSet(key: String?): Boolean
    fun remove(key: String?): Boolean
}