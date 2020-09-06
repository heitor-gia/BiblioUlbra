package com.hgianastasio.biblioulbrav2.core.settings.constants

/**
 * Created by heitor_12 on 06/06/17.
 */
enum class DefaultSetting(val key: String, val defaultValue: String, val title: String, val unit: String) {
    loansNotification("loansNotification", "1", "Notificação de empréstimos", "dia(s)");

    companion object {
        fun findByKey(key: String) = values().find { it.key == key }
    }

}