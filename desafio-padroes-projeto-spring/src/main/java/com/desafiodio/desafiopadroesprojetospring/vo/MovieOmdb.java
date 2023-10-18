package com.desafiodio.desafiopadroesprojetospring.vo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MovieOmdb {

    @JsonProperty("Title") //Na API, o title está com T maiúsculo, o que deria erro no código. Essa anotação resolve.
    private String title;
    @JsonProperty("Year")
    private  String year;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }




}
