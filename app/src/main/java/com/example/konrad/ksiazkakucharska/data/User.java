package com.example.konrad.ksiazkakucharska.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Konrad on 2015-01-15.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //prevents crashes after changing json structure
public class User implements Serializable {
    public Integer id;

    @JsonProperty("session_id")
    public String sessionId;
}
