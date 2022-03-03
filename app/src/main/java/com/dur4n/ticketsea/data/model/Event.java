package com.dur4n.ticketsea.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@Entity
public class Event implements Serializable , Comparable{
    public static final String TAG = "EVENT";
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    String nombre;
    String ownerAddress;
    @NonNull
    long date;
    String picture;
    @NonNull
    String description;
    @Ignore
    HashMap<String, Ticket> tickets;
    @NonNull
    Double comission;
    //TODO create and add a arrayList of ticket lot object
    //TODO comision


    public Event(
            int id,
            String nombre,
            long date,
            String description,
            Double comission
    ) {
        this.id = id;
        this.nombre = nombre;
        this.date = date;
        this.description = description;
        this.comission = comission;
    }

    @Ignore
    public Event(
            String nombre,
            String ownerAddress,
            long date,Double comission,
            String picture,
            String description
    ) {
        this.nombre = nombre;
        this.ownerAddress = ownerAddress;
        this.date = date;
        this.picture = picture;
        this.description = description;
        this.tickets = new HashMap<>();
        this.comission = comission;
    }
    @Ignore
    public Event(
            String nombre,
            long date,
            String description,
            Double comission,
            HashMap<String, Ticket> tickets
    ) {
        this.nombre = nombre;
        this.date = date;
        this.description = description;
        this.tickets = tickets;
        this.comission = comission;
    }

    @Ignore
    public Event(){}
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(HashMap<String, Ticket> tickets) {
        this.tickets = tickets;
    }

    public Double getComission() {
        return comission;
    }

    public void setComission(Double comission) {
        this.comission = comission;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((Event) obj).getNombre().equals(((Event)obj).getNombre());
    }

    @Override
    public String toString() {
        return "Event{" +
                "nombre='" + nombre + '\'' +
                ", ownerAddress='" + ownerAddress + '\'' +
                ", date='" + new Date(date) + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        if(equals(o))
            return ((Event)o).getDescription().compareTo(((Event)o).getDescription());
        else return ((Event) o).getNombre().compareTo(getNombre());
    }

}
