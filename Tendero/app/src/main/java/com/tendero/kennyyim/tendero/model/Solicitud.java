package com.tendero.kennyyim.tendero.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kenny Yim on 21/08/2017.
 */

public class Solicitud implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("textSolicitud")
    private String textSolicitud;

    @SerializedName("responsable")
    private String responsable;

    @SerializedName("fechaInicio")
    private String fechaInicio;

    @SerializedName("fechaFinal")
    private String fechaFinal;


    public Solicitud() {
    }

    public Solicitud(String id, String textSolicitud, String responsable, String fechaInicio, String fechaFinal) {
        this.id = id;
        this.textSolicitud = textSolicitud;
        this.responsable = responsable;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
