package com.hgianastasio.biblioulbrav2.core.search.constants;

import java.util.ArrayList;

/**
 * Created by heitor_12 on 23/11/16.
 */
public enum SearchMedia {
    All(0,"","Todos"),
    Printed(1,"IMPRE","Livro/Folheto/TCC/Tese/Diss/Monog"),
    NewsPapers(2,"ISSUE","Periódico"),
    SoundCD(3,"CD","CD sonoro"),
    CDRom(4,"CDROM","CD-ROM"),
    Diap(5,"DIAP","Diapositivo"),
    Disket(6,"DISQ","Disquete"),
    DVD(7,"DVD","DVD"),
    K7(8,"K7","Fita cassete"),
    Map(9,"MAPA","Mapa/Globo"),
    Video(10,"VIDEO","Vídeo");


    private int id;
    private String name;
    private String code;

    SearchMedia(int id, String code, String name){
        this.code = code;
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<>();
        for (SearchMedia media:
                values()) {
            names.add(media.getName());
        }
        return names;
    }

    public static SearchMedia findByCode(String code){
        for(SearchMedia media: values())
            if (media.code.equalsIgnoreCase(code))
                return media;

        return null;
    }
    public static SearchMedia findById(int id){
        for(SearchMedia media: values())
            if (media.id == id)
                return media;

        return null;
    }
}
