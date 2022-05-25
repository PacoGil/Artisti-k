package com.example.artisti_k;

import android.os.Parcel;
import android.os.Parcelable;

public class CompraEntradas implements Parcelable {

    String idEvento;
    String artista;
    String lugarEvento;
    String fechaLugarEvento;
    String entradas;
    String precioTotal;

    public CompraEntradas(String idEvento , String artista, String lugarEvento, String fechaLugarEvento, String entradas, String precioTotal) {

        this.idEvento = idEvento;
        this.artista = artista;
        this.lugarEvento = lugarEvento;
        this.fechaLugarEvento = fechaLugarEvento;
        this.entradas = entradas;
        this.precioTotal = precioTotal;
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

    public String getEntradas() {
        return entradas;
    }

    public void setEntradas(String entradas) {
        this.entradas = entradas;
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

        dest.writeString(this.idEvento);
        dest.writeString(this.artista);
        dest.writeString(this.lugarEvento);
        dest.writeString(this.fechaLugarEvento);
        dest.writeString(this.entradas);
        dest.writeString(this.precioTotal);
    }

    public void readFromParcel(Parcel source) {
        this.idEvento = source.readString();
        this.artista = source.readString();
        this.lugarEvento = source.readString();
        this.fechaLugarEvento = source.readString();
        this.entradas = source.readString();
        this.precioTotal = source.readString();
    }

    protected CompraEntradas(Parcel in) {

        this.idEvento = in.readString();
        this.artista = in.readString();
        this.lugarEvento = in.readString();
        this.fechaLugarEvento = in.readString();
        this.entradas = in.readString();
        this.precioTotal = in.readString();
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
