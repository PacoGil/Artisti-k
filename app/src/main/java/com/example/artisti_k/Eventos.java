package com.example.artisti_k;

import java.util.HashMap;

public class Eventos {
    HashMap<String, Evento> eventos = new HashMap<>();

    public Eventos() {
    }

    public Evento getEvento(String id) {
        System.out.println("GET EVENTO === " + eventos);
        return eventos.get(id);
    }

    public void setEvento(String id, Evento evento) {
        System.out.println("id === " + id);
        System.out.println("evento === " + evento);
        eventos.put(id, evento);
    }

}
