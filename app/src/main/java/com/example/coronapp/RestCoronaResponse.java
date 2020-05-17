package com.example.coronapp;

import java.util.List;

public class RestCoronaResponse {
    private List<Corona> Countries;
    private String Date;

    /*
    public ArrayList<Global> getGlobal() {
        return Global;
    }
*/
    public List<Corona> getCountries() {

        return Countries;
    }

    public String getDate() {

        return Date;
    }
}
