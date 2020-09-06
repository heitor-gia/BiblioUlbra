package com.hgianastasio.biblioulbrav2.core.settings.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.settings.Setting
import com.hgianastasio.biblioulbrav2.core.settings.constants.DefaultSetting
import com.hgianastasio.biblioulbrav2.core.settings.exceptions.SettingNotFoundException
import com.hgianastasio.biblioulbrav2.core.settings.repository.SettingRepository
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject

/**
 * Created by heitor_12 on 03/06/17.
 */
class GetSetting @Inject constructor(executor: ThreadPoolExecutor, private val repository: SettingRepository) : UseCase<Setting?, String>(executor) {
    override fun process(key: String, resultListener: OnResultListener<Setting?>, errorListener: OnErrorListener) {
        try {
            if (repository.isSet(key)) repository[key, resultListener] else getDefaultSetting(key, resultListener)
        } catch (e: Exception) {
            errorListener(e)
        }
    }

    @Throws(Exception::class)
    private fun getDefaultSetting(key: String, resultListener: OnResultListener<Setting?>) {
        val defaultSetting: DefaultSetting? = DefaultSetting.findByKey(key)
        if (defaultSetting != null) resultListener(Setting.fromDefaultSetting(defaultSetting)) else throw SettingNotFoundException(key)
    }

}