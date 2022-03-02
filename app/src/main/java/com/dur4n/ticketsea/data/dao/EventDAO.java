package com.dur4n.ticketsea.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dur4n.ticketsea.data.model.Event;

import java.util.List;

@Dao
public interface EventDAO {
    @Insert
    long insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("DELETE FROM event WHERE nombre=:eventName")
    void deleteEventByEventName(String eventName);

    @Query("DELETE FROM event")
    void deleteAll();

    @Query("SELECT * FROM event ORDER BY nombre ASC")
    List<Event> select();

    @Query("SELECT * FROM event WHERE nombre=:nombre")
    Event findByName(String nombre);
}
