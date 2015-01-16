package com.example.konrad.ksiazkakucharska.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by Konrad on 2015-01-15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe implements Serializable {
    public String title;
    public String introduction;
    public String ingredients;
    public String steps;
    public String created;
    public String preparationMinutes;
    public String cookingMinutes;
    public String servings;
}
