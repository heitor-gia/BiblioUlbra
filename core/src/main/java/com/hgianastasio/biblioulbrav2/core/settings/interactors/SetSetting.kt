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
class SetSetting @Inject constructor(executor: ThreadPoolExecutor, var repository: SettingRepository) : UseCase<Void?, Setting?>(executor) {
    override fun process(params: Setting?, resultListener: OnResultListener<Void?>, errorListener: OnErrorListener) {
        try {
            repository.set(params)
            resultListener(null)
        } catch (e: Exception) {
            errorListener(RuntimeException("Não foi possível atualizar a configuração", e))
        }
    }

}