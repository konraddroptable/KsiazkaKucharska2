package com.example.konrad.ksiazkakucharska.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Konrad on 2015-01-15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailAndPassword {
    public String email;
    public String password;
}
