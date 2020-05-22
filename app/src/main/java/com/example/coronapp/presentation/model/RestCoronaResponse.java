package com.example.coronapp.presentation.model;

import java.util.List;

public class RestCoronaResponse {
    private List<Corona> Countries;
    private String Date;

    public RestCoronaResponse() {
    }

    public List<Corona> getCountries() {

        return Countries;
    }

    public String getDate() {

        return Date;
    }
}
