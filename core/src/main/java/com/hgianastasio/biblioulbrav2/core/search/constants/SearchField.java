package com.hgianastasio.biblioulbrav2.core.search.constants;

import java.util.ArrayList;

/**
 * Created by heitor_12 on 23/11/16.
 */
public enum SearchField {

    AllFields(0,"WRD" ,"Todos os campos"),
    Author(1,"WAU" ,"Autor"),
    Subject(2,"WSU" ,"Assunto"),
    Serie(3,"WSE" ,"Série"),
    AcademicPublications(4,"WTE" ,"Nota Tese/Diss/Monog"),
    BarCode(5,"BAR" ,"Código de Barras");

    private int id;
    private String code;
    private String name;

    SearchField(int id,String code, String name){
        this.code = code;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
    public static ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<>();
        for (SearchField field :
                values()) {
            names.add(field.getName());
        }
        return names;
    }

    public static SearchField findByCode(String code){
        for(SearchField field: values())
            if (field.code.equalsIgnoreCase(code))
                return field;

        return null;
    }
    public static SearchField findById(int id){
        for(SearchField field: values())
            if (field.id == id)
                return field;

        return null;
    }
}
