package com.example.artisti_k;

import android.content.Intent;

public class ConfirmacionCompra {

    String entradas;
    String total;
    String artista;
    String lugar;
    String fecha;
    String fechaCompra;
    String idEvento;
    String id;

    public ConfirmacionCompra() {
    }



    public ConfirmacionCompra(String entradas, String total, String artista, String lugar, String fecha, String fechaCompra, String id, String idEvento) {

        this.entradas = entradas;
        this.total = total;
        this.artista = artista;
        this.lugar = lugar;
        this.fecha = fecha;
        this.fechaCompra = fechaCompra;
        this.idEvento = idEvento;
        this.id = id;
    }

    public ConfirmacionCompra(String entradas, String total, String artista, String lugar, String id, String idEvento) {
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

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
