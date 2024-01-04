package com.example.conserjera;

public class Vehiculo {
    private String vehiculoId;
    private String patente;
    private String marca;
    private String color;

    public Vehiculo() {
        // Constructor vacío requerido por Firebase
    }

    public Vehiculo(String vehiculoId, String patente, String marca, String color) {
        this.vehiculoId = vehiculoId;
        this.patente = patente;
        this.marca = marca;
        this.color = color;
    }

    public String getVehiculoId() {
        return vehiculoId;
    }

    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Patente: " + patente + "\nMarca: " + marca + "\nColor: " + color;
    }

}
