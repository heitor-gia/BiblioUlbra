package com.hgianastasio.biblioulbrav2.core.search.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by heitor_12 on 26/10/16.
 */
public enum SearchBase {



    Todas(0,"Todas Bibliotecas da ULBRA", "ULB01"),
    Canoas(1,"Campus Canoas", "CANOAS"),
    Cachoeira(2,"Campus Cachoeira do Sul", "CACHOEIRA"),
    Carazinho(3,"Campus Carazinho", "CARAZINHO"),
    Guaiba(4,"Campus Guaíba", "GUAIBA"),
    PortoAlegre(5,"Campus Porto Alegre", "PORTOALEGRE"),
    SantaMaria(6,"Campus Santa Maria", "SANTAMARIA"),
    SaoJeronimo(7,"Campus São Jerônimo", "SAOJER"),
    Torres(8,"Campus Torres", "TORRES"),
    HUC(9,"Hospital Universitário de Canoas", "HUC"),
    Sconcordia(10,"Seminário Concórdia", "SCONCORDIA"),
    Jiparana(11,"Centro Universitário de Ji-Paraná", "JIPARANA"),
    Manaus(12,"Centro Universitário de Manaus", "MANAUS"),
    Palmas(13,"Centro Universitário de Palmas", "PALMAS"),
    Santarem(14,"Centro Universitário de Santarém", "SANTAREM"),
    Itumbiara(15,"ILES de Itumbiara", "ITUMBIARA"),
    PortoVelho(16,"ILES de Porto Velho", "PORTOVELHO"),
    Concordia(17,"UE Concórdia", "CONCORDIA"),
    CristoRedentor(18,"UE Cristo Redentor", "CRISTO"),
    EspConcordia(19,"UE Especial Concórdia", "ESP-CONCORDIA"),
    UEML(20,"UE Martinho Lutero", "UEML"),
    Paz(21,"UE Paz", "PAZ"),
    SaoJoao(22,"UE São João", "SAOJOAO"),
    SaoLucas(23,"UE São Lucas", "SAOLUCAS"),
    SaoMarcos(24,"UE São Marcos", "SAOMARCOS"),
    SaoMateus(25,"UE São Mateus", "SAOMATEUS"),
    SaoPedro(26,"UE São Pedro", "SAOPEDRO"),
    CEML(27,"UE CEML", "CEML"),
    Aplicacao(28,"UE Escola Aplicação", "APLICACAO"),
    Antares(29,"UE Colégio Antares", "ANTARES"),
    Ceducs(30,"UE CEDUCS", "CEDUCS"),
    Cedusp(31,"UE CEDUSP", "CEDUSP"),
    Livro(32,"Livros, Folhetos, Obras de Referência", "LIVRO"),
    Periodico(33,"Periódicos, Revistas, Jornais", "PERIODICO"),
    Multimeio(34,"Multimeios (Materiais Especiais)", "MULTIMEIO"),
    TCC(35,"TCCs - Trabalhos de Conclusão de Cursos", "TCC"),
    Mono(36,"Monografias de Especialização", "MONO"),
    Tese(37,"Teses e Dissertações", "TESE"),
    BDMono(38,"BDMONO - Monografias de Especialização On-line", "BDMONO"),
    BDTese(39,"BDTD - Teses e Dissertações On-line", "BDTESE"),
    Artigo(40,"Artigos de Periódicos", "ARTIGO"),
    ProdCient(41,"Produção Científica", "PRODCIENT"),
    Norma(42,"Normas Técnicas", "NORMA"),
    BaseDados(43,"Bases de Dados", "BASEDADOS"),
    Online(44,"Documentos On-line", "ONLINE");

    private int id;
    private String code;
    private String name;

    SearchBase(int id,String name, String code){
        this.name = name;
        this.code = code;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return this.name;
    }

    public String getCode(){
        return this.code;
    }

    public static ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<>();
        for (SearchBase base:
             values()) {
            names.add(base.getName());
        }
        return names;
    }

    public static SearchBase findByCode(String code){
        for(SearchBase base: values())
            if (base.code.equalsIgnoreCase(code))
                return base;

        return null;
    }
    public static SearchBase findById(int id){
        for(SearchBase base: values())
            if (base.id == id)
                return base;

        return null;
    }
}
