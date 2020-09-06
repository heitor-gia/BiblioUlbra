package com.hgianastasio.biblioulbrav2.core.settings.exceptions

/**
 * Created by heitor_12 on 06/06/17.
 */
class SettingNotFoundException(private val key: String) : RuntimeException() {
    override val message: String
        get() = "A configuração $key não foi encontrada."

}