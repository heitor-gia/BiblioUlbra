package com.hgianastasio.biblioulbrav2.core.search.constants

/**
 * Created by heitor_12 on 23/11/16.
 */
enum class SearchMedia(val id: Int, val code: String, val title: String) {
    All(0, "", "Todos"),
    Printed(1, "IMPRE", "Livro/Folheto/TCC/Tese/Diss/Monog"),
    NewsPapers(2, "ISSUE", "Periódico"),
    SoundCD(3, "CD", "CD sonoro"),
    CDRom(4, "CDROM", "CD-ROM"),
    Diap(5, "DIAP", "Diapositivo"),
    Disket(6, "DISQ", "Disquete"),
    DVD(7, "DVD", "DVD"),
    K7(8, "K7", "Fita cassete"),
    Map(9, "MAPA", "Mapa/Globo"),
    Video(10, "VIDEO", "Vídeo");

    companion object {
        val names: List<String>
            get() = values().toList().map { it.title }

        fun findByCode(code: String?) = values().find { it.code.equals(code, true) }

        fun findById(id: Int)  = values().find { it.id == id }
    }

}