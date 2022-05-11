package com.example.artisti_k;

public class CompraEntradas {
    String entradas;  // numero de entradas elegidas
    String comprador; // usuario email
    String evento;    // evento elegido
    String lugarEvento; //
    String fechaLugarEvento; //

    public CompraEntradas(String entradas, String comprador, String evento, String lugarEvento, String fechaLugarEvento) {
        this.entradas = entradas;
        this.comprador = comprador;

        this.evento = evento;
        this.lugarEvento = lugarEvento;
        this.fechaLugarEvento = fechaLugarEvento;
    }

    public String getEntradas() {
        return entradas;
    }

    public void setEntradas(String entradas) {
        this.entradas = entradas;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
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
}
