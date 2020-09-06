package com.hgianastasio.biblioulbrav2.core.search.constants

/**
 * Created by heitor_12 on 26/10/16.
 */
enum class SearchBase(val id: Int, val title: String, val code: String) {
    Todas(0, "Todas Bibliotecas da ULBRA", "ULB01"),
    Canoas(1, "Campus Canoas", "CANOAS"),
    Cachoeira(2, "Campus Cachoeira do Sul", "CACHOEIRA"),
    Carazinho(3, "Campus Carazinho", "CARAZINHO"),
    Guaiba(4, "Campus Guaíba", "GUAIBA"),
    PortoAlegre(5, "Campus Porto Alegre", "PORTOALEGRE"),
    SantaMaria(6, "Campus Santa Maria", "SANTAMARIA"),
    SaoJeronimo(7, "Campus São Jerônimo", "SAOJER"),
    Torres(8, "Campus Torres", "TORRES"),
    HUC(9, "Hospital Universitário de Canoas", "HUC"),
    Sconcordia(10, "Seminário Concórdia", "SCONCORDIA"),
    Jiparana(11, "Centro Universitário de Ji-Paraná", "JIPARANA"),
    Manaus(12, "Centro Universitário de Manaus", "MANAUS"),
    Palmas(13, "Centro Universitário de Palmas", "PALMAS"),
    Santarem(14, "Centro Universitário de Santarém", "SANTAREM"),
    Itumbiara(15, "ILES de Itumbiara", "ITUMBIARA"),
    PortoVelho(16, "ILES de Porto Velho", "PORTOVELHO"),
    Concordia(17, "UE Concórdia", "CONCORDIA"),
    CristoRedentor(18, "UE Cristo Redentor", "CRISTO"),
    EspConcordia(19, "UE Especial Concórdia", "ESP-CONCORDIA"),
    UEML(20, "UE Martinho Lutero", "UEML"),
    Paz(21, "UE Paz", "PAZ"),
    SaoJoao(22, "UE São João", "SAOJOAO"),
    SaoLucas(23, "UE São Lucas", "SAOLUCAS"),
    SaoMarcos(24, "UE São Marcos", "SAOMARCOS"),
    SaoMateus(25, "UE São Mateus", "SAOMATEUS"),
    SaoPedro(26, "UE São Pedro", "SAOPEDRO"),
    CEML(27, "UE CEML", "CEML"),
    Aplicacao(28, "UE Escola Aplicação", "APLICACAO"),
    Antares(29, "UE Colégio Antares", "ANTARES"),
    Ceducs(30, "UE CEDUCS", "CEDUCS"),
    Cedusp(31, "UE CEDUSP", "CEDUSP"),
    Livro(32, "Livros, Folhetos, Obras de Referência", "LIVRO"),
    Periodico(33, "Periódicos, Revistas, Jornais", "PERIODICO"),
    Multimeio(34, "Multimeios (Materiais Especiais)", "MULTIMEIO"),
    TCC(35, "TCCs - Trabalhos de Conclusão de Cursos", "TCC"),
    Mono(36, "Monografias de Especialização", "MONO"),
    Tese(37, "Teses e Dissertações", "TESE"),
    BDMono(38, "BDMONO - Monografias de Especialização On-line", "BDMONO"),
    BDTese(39, "BDTD - Teses e Dissertações On-line", "BDTESE"),
    Artigo(40, "Artigos de Periódicos", "ARTIGO"),
    ProdCient(41, "Produção Científica", "PRODCIENT"),
    Norma(42, "Normas Técnicas", "NORMA"),
    BaseDados(43, "Bases de Dados", "BASEDADOS"),
    Online(44, "Documentos On-line", "ONLINE");

    companion object {
        val titles: List<String>
            get() = values().toList().map { it.title }

        fun findByCode(code: String?) = values().find { it.code.equals(code, true) }

        fun findById(id: Int)= values().find { it.id == id }
    }

}