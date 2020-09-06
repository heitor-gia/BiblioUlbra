package com.hgianastasio.biblioulbrav2.core.search.constants

/**
 * Created by heitor_12 on 26/10/16.
 */
enum class SearchLanguage(val id: Int, val code: String, val title: String) {
    All(0, "", "Todas"),
    Portuguese(1, "POR", "Português"),
    English(2, "ENG", "Inglês"),
    German(3, "GER", "Alemão"),
    Spanish(4, "SPA", "Espanhol"),
    French(5, "FRE", "Francês");

    companion object {
        val names: List<String>
            get() = values().toList().map { it.title }

        fun findByCode(code: String?) = values().find { it.code.equals(code, true) }

        fun findById(id: Int)  = values().find { it.id == id }
    }

}