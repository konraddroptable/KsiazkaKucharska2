package com.example.konrad.ksiazkakucharska.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2015-01-28.
 */
public class UserList {

    @JsonProperty("record")
    public List<User> records = new ArrayList<User>();
}
