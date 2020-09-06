package com.hgianastasio.biblioulbrav2.data.models.settings

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper
import com.hgianastasio.biblioulbrav2.core.settings.Setting
import javax.inject.Inject

/**
 * Created by heitor_12 on 04/06/17.
 */
class SettingEntityMapper @Inject constructor() : Mapper<Setting, SettingEntity>() {
    override fun transform(input: Setting): SettingEntity {
        return SettingEntity(input.key!!, input.value)
    }

    override fun transformBack(input: SettingEntity): Setting? {
        return Setting(input.key, input.value)
    }
}