package com.hgianastasio.biblioulbrav2.core.settings.interactors

import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.settings.Setting
import com.hgianastasio.biblioulbrav2.core.settings.repository.SettingRepository
import java.util.concurrent.ThreadPoolExecutor
import javax.inject.Inject

/**
 * Created by heitor_12 on 03/06/17.
 */
class GetAllSettings @Inject constructor(executor: ThreadPoolExecutor, private val repository: SettingRepository) : UseCase<List<Setting?>?, Void?>(executor) {
    override fun process(params: Void?, resultListener: OnResultListener<List<Setting?>?>, errorListener: OnErrorListener) {
        try {
            repository.getAll(resultListener)
        } catch (e: Exception) {
            errorListener(e)
        }
    }

}