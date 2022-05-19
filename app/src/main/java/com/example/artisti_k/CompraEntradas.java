package com.example.artisti_k;

public class CompraEntradas {

    String entradas;  // numero de entradas elegidas
    String artista;    // evento elegido
    String lugarEvento; // lugar del evento
    String fechaLugarEvento; // fecha del evento
    String precioTotal; // precio total de las entradas
    String idEvento;

    public CompraEntradas(String idEvento , String entradas, String artista, String lugarEvento, String fechaLugarEvento, String precioTotal) {
        this.idEvento = idEvento;
        this.entradas = entradas;
        this.artista = artista;
        this.lugarEvento = lugarEvento;
        this.fechaLugarEvento = fechaLugarEvento;
        this.precioTotal = precioTotal;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getEntradas() {
        return entradas;
    }

    public void setEntradas(String entradas) {
        this.entradas = entradas;
    }

    public String getEvento() {
        return artista;
    }

    public void setEvento(String evento) {
        this.artista = evento;
    }

    public String getLugarEvento() {
        return lugarEvento;
    }

    public void setLugarEvento(String lugarEvento) {
        this.lugarEvento = lugarEvento;
    }

    public String getFechaLugarEvento() {
        return fechaLugarEvento;
    }

    public void setFechaLugarEvento(String fechaLugarEvento) {
        this.fechaLugarEvento = fechaLugarEvento;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }
}
