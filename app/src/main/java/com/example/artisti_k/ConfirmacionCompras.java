package com.example.artisti_k;

import java.util.HashMap;

public class ConfirmacionCompras {

    HashMap<String, ConfirmacionCompra> confirmacionCompras = new HashMap<>();

    public ConfirmacionCompras() {
    }

    public ConfirmacionCompra getConfirmacionCompra(String id) {
        System.out.println("GET EVENTO === " + confirmacionCompras);
        return confirmacionCompras.get(id);
    }

    public void setConfirmacionCompra(String id, ConfirmacionCompra confirmacionCompra) {
        System.out.println("id === " + id);
        System.out.println("evento === " + confirmacionCompra);
        confirmacionCompras.put(id, confirmacionCompra);
    }
}
