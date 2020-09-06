package com.hgianastasio.biblioulbrav2.system

import com.hgianastasio.biblioulbrav2.core.settings.interactors.GetSetting
import com.hgianastasio.biblioulbrav2.core.settings.interactors.SetSetting

/**
 * Created by heitor_12 on 06/06/17.
 */
class SettingsManager {
    var getSetting: GetSetting? = null
    var setSetting: SetSetting? = null
    var executor: UseCaseExecutor? = null
}