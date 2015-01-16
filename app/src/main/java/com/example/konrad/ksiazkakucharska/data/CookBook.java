package com.example.konrad.ksiazkakucharska.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2015-01-15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CookBook {

    @JsonProperty("record")
    public List<Recipe> records = new ArrayList<Recipe>();
}
