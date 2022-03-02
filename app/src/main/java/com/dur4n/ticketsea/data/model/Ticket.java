package com.dur4n.ticketsea.data.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Event.class,parentColumns = "id", childColumns = "event_id", onDelete = CASCADE))
public class Ticket implements Serializable {
    public static final String TAG = "TICKET";
    @PrimaryKey(autoGenerate=true)
    public int id;
    @ColumnInfo(name="event_id")
    public int eventId;
    String eventName;
    @NonNull
    String referenceCode;
    @NonNull
    String price;
    String picture;
    @Ignore
    Integer count;

    public Ticket(
            int id,
            int eventId,
            String eventName,
            @NonNull String referenceCode,
            @NonNull String price
    ) {
        this.id = id;
        this.eventId = eventId;
        this.eventName = eventName;
        this.referenceCode = referenceCode;
        this.price = price;
        this.count = count;
    }

    @Ignore
    public Ticket(){

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", referenceCode='" + referenceCode + '\'' +
                ", price='" + price + '\'' +
                ", picture='" + picture + '\'' +
                ", count=" + count +
                '}';
    }
}
