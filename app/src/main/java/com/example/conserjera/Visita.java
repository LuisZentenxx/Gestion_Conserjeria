package com.example.conserjera;

public class Visita {
    private String visitaId;
    private String nombre;
    private String rut;
    private String motivo;

    public Visita() {
        // Constructor vacío requerido por Firebase
    }

    public Visita(String visitaId, String nombre, String rut, String motivo) {
        this.visitaId = visitaId;
        this.nombre = nombre;
        this.rut = rut;
        this.motivo = motivo;
    }

    public String getVisitaId() {
        return visitaId;
    }

    public String getNombre() { return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nRUT: " + rut + "\nMotivo: " + motivo;
    }

}
