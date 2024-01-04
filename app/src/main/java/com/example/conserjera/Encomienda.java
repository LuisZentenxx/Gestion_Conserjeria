package com.example.conserjera;

public class Encomienda {
    private String encomiendaId;
    private String remitente;
    private String destinatario;
    private String encomienda;
    private String departamento;
    private String fecha;

    public Encomienda() {
        // Constructor vacío requerido por Firebase
    }

    public Encomienda(String encomiendaId, String remitente, String destinatario, String encomienda, String departamento, String fecha) {
        this.encomiendaId = encomiendaId;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.encomienda = encomienda;
        this.departamento = departamento;
        this.fecha = fecha;
    }

    public String getEncomiendaId() {
        return encomiendaId;
    }

    public String getRemitente() { return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getEncomienda() {
        return encomienda;
    }

    public String getDepartamento() {
        return departamento;
    }
    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Nombre: " + remitente + "\nDestinatario: " + destinatario + "\nEncomienda: " + encomienda + "\nDepartamento: "
                + departamento + "\nFecha: " + fecha;
    }

}
