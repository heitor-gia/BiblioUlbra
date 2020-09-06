package com.hgianastasio.biblioulbrav2.core.search.constants

/**
 * Created by heitor_12 on 23/11/16.
 */
enum class SearchField(val id: Int, val code: String, val title: String) {
    AllFields(0, "WRD", "Todos os campos"),
    Author(1, "WAU", "Autor"),
    Subject(2, "WSU", "Assunto"),
    Serie(3, "WSE", "Série"),
    AcademicPublications(4, "WTE", "Nota Tese/Diss/Monog"),
    BarCode(5, "BAR", "Código de Barras");

    companion object {
        val names: List<String>
            get() = values().toList().map { it.title }

        fun findByCode(code: String?) = values().find { it.code.equals(code, true) }

        fun findById(id: Int)  = values().find { it.id == id }
    }

}