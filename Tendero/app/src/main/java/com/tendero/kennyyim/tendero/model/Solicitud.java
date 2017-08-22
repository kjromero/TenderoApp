package com.tendero.kennyyim.tendero.model;

import java.io.Serializable;

/**
 * Created by Kenny Yim on 21/08/2017.
 */

public class Solicitud implements Serializable {

    private String id;

    private String textSolicitud;

    private String responsable;


    public Solicitud() {
    }

    public Solicitud(String id, String textSolicitud, String responsable) {
        this.id = id;
        this.textSolicitud = textSolicitud;
        this.responsable = responsable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTextSolicitud() {
        return textSolicitud;
    }

    public void setTextSolicitud(String textSolicitud) {
        this.textSolicitud = textSolicitud;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
