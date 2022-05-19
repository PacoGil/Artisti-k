package com.example.artisti_k;

import android.os.Parcel;
import android.os.Parcelable;

public class CompraEntradas implements Parcelable {

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

    public String getArtista() {
        return artista;
    }

    public void setArtista(String evento) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.entradas);
        dest.writeString(this.artista);
        dest.writeString(this.lugarEvento);
        dest.writeString(this.fechaLugarEvento);
        dest.writeString(this.precioTotal);
        dest.writeString(this.idEvento);
    }

    public void readFromParcel(Parcel source) {
        this.entradas = source.readString();
        this.artista = source.readString();
        this.lugarEvento = source.readString();
        this.fechaLugarEvento = source.readString();
        this.precioTotal = source.readString();
        this.idEvento = source.readString();
    }

    protected CompraEntradas(Parcel in) {
        this.entradas = in.readString();
        this.artista = in.readString();
        this.lugarEvento = in.readString();
        this.fechaLugarEvento = in.readString();
        this.precioTotal = in.readString();
        this.idEvento = in.readString();
    }

    public static final Parcelable.Creator<CompraEntradas> CREATOR = new Parcelable.Creator<CompraEntradas>() {
        @Override
        public CompraEntradas createFromParcel(Parcel source) {
            return new CompraEntradas(source);
        }

        @Override
        public CompraEntradas[] newArray(int size) {
            return new CompraEntradas[size];
        }
    };
}
