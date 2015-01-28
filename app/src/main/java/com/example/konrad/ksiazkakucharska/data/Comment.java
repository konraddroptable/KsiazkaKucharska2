package com.example.konrad.ksiazkakucharska.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Konrad on 2015-01-15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment implements Serializable {
    public int id;
    public int recipeId;
    public Integer ownerId;
    public String text;
    public String created;

    @JsonIgnore
    public String displayName;
}
