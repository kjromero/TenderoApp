package com.tendero.kennyyim.tendero.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kenny Yim on 23/08/2017.
 */

public class User implements Serializable {

    @SerializedName("email")
    private String email;

    @SerializedName("rol")
    private String rol;

    public User() {
    }

    public User(String email, String rol) {
        this.email = email;
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
