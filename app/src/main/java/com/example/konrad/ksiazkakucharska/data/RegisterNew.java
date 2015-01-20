package com.example.konrad.ksiazkakucharska.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Konrad on 2015-01-20.
 */
@JsonIgnoreProperties(ignoreUnknown = true) //prevents crashes after changing json structure
public class RegisterNew {

    public int id;
    public String email;
    @JsonProperty("display_name")
    public String displayName;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("last_name")
    public String lastName;
    @JsonProperty("new_password")
    public String newPassword;

    @JsonProperty("session_id")
    public String sessionId;

}
