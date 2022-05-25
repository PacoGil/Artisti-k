package com.example.artisti_k;

public class ConfirmacionCompra {

    String id;
    String idEvento;
    String artista;
    String lugar;
    String fecha;
    String entradas;
    String total;
    String fechaCompra;
    String usuario;

    public ConfirmacionCompra() {
    }

    public ConfirmacionCompra(String id, String idEvento, String artista, String lugar, String fecha, String entradas, String total, String fechaCompra, String usuario) {

        this.id = id;
        this.idEvento = idEvento;
        this.artista = artista;
        this.lugar = lugar;
        this.fecha = fecha;
        this.entradas = entradas;
        this.total = total;
        this.fechaCompra = fechaCompra;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEntradas() {
        return entradas;
    }

    public void setEntradas(String entradas) {
        this.entradas = entradas;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String email) {
        this.usuario = usuario;
    }
}
