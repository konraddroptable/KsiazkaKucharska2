package com.example.konrad.ksiazkakucharska.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by Konrad on 2015-01-15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    public int id;
    public int recipeId;
    public int ownerId;
    public String text;
    public String created;
}
