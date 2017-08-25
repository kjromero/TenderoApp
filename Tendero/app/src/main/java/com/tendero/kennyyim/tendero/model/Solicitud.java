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

    @SerializedName("estado")
    private String estado;

    @SerializedName("comentarioSoporte")
    private String comentarioSoporte;

    public Solicitud() {
    }

    public Solicitud(String id, String textSolicitud, String responsable, String fechaInicio, String fechaFinal, String estado, String comentarioSoporte) {
        this.id = id;
        this.textSolicitud = textSolicitud;
        this.responsable = responsable;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
        this.comentarioSoporte = comentarioSoporte;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarioSoporte() {
        return comentarioSoporte;
    }

    public void setComentarioSoporte(String comentarioSoporte) {
        this.comentarioSoporte = comentarioSoporte;
    }
}
