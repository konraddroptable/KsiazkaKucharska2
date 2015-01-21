package com.example.konrad.ksiazkakucharska.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Konrad on 2015-01-21.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultWithId {
    public int id;
}
