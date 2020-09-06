package com.hgianastasio.biblioulbrav2.data.repositories

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.settings.Setting
import com.hgianastasio.biblioulbrav2.core.settings.repository.SettingRepository
import com.hgianastasio.biblioulbrav2.data.base.setting.SettingOnDisk
import com.hgianastasio.biblioulbrav2.data.models.settings.SettingEntityMapper
import java.io.IOException
import javax.inject.Inject

/**
 * Created by heitor_12 on 03/06/17.
 */
class SettingRepositoryImpl @Inject constructor(private val disk: SettingOnDisk, private val mapper: SettingEntityMapper) : SettingRepository {
    @Throws(IOException::class)
    override fun get(key: String?, resultListener: OnResultListener<Setting?>) {
        resultListener(disk[key]?.let(mapper::transformBack))
    }

    @Throws(IOException::class)
    override fun getAll(resultListener: OnResultListener<List<Setting>>) {
        resultListener(mapper.tansformBack(disk.all))
    }

    override fun set(setting: Setting?): Boolean {
        return disk.set(setting?.let(mapper::transform))
    }

    override fun isSet(key: String?): Boolean {
        return disk.isSet(key)
    }

    override fun remove(key: String?): Boolean {
        return disk.remove(key)
    }

}