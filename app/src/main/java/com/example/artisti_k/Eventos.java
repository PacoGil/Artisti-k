package com.example.artisti_k;

public class Eventos {
    String artista;
    String lugar;
    String fecha;
    String hora;
    String id;

    public Eventos(String artista, String lugar, String fecha, String hora, String id) {
        this.artista = artista;
        this.lugar = lugar;
        this.fecha = fecha;
        this.hora = hora;
        this.id = id;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
