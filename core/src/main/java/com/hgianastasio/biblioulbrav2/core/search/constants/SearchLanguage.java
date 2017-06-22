package com.hgianastasio.biblioulbrav2.core.search.constants;

import java.util.ArrayList;

/**
 * Created by heitor_12 on 26/10/16.
 */
public enum SearchLanguage {



    All(0,"", "Todas"),
    Portuguese(1,"POR", "Português"),
    English(2,"ENG", "Inglês"),
    German(3,"GER", "Alemão"),
    Spanish(4,"SPA", "Espanhol"),
    French(5,"FRE", "Francês");

    private int id;
    private String code;
    private String name;

    SearchLanguage(int id,String code, String name){
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
        for (SearchLanguage base:
             values()) {
            names.add(base.getName());
        }
        return names;
    }

    public static SearchLanguage findByCode(String code){
        for(SearchLanguage language: values())
            if (language.code.equalsIgnoreCase(code))
                return language;

        return null;
    }
    public static SearchLanguage findById(int id){
        for(SearchLanguage language: values())
            if (language.id == id)
                return language;

        return null;
    }
}
