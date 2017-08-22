package com.tendero.kennyyim.tendero.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kenny Yim on 21/08/2017.
 */

public class Producto implements Serializable{

    @SerializedName("id")
    private String id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("cantidad")
    private int cantidad;

    @SerializedName("valor")
    private double valor;

    public Producto() {
    }

    public Producto(String id, String nombre, int cantidad, double valor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
