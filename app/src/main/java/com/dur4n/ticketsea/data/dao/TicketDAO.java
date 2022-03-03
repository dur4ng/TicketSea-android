package com.dur4n.ticketsea.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dur4n.ticketsea.data.model.Event;
import com.dur4n.ticketsea.data.model.Ticket;

import java.util.List;

@Dao
public interface TicketDAO {
    @Insert
    long insert(Ticket ticket);

    @Update
    void update(Ticket ticket);

    @Delete
    void delete(Ticket ticket);

    @Query("DELETE FROM ticket")
    void deleteAll();

    @Query("SELECT * FROM ticket")
    List<Ticket> select();

    @Query("SELECT * FROM ticket WHERE eventName=:eventName")
    List<Ticket> getAllTicketsEvent(String eventName);

    @Query("SELECT * FROM ticket WHERE referenceCode=:referenceCode")
    Ticket findByreferenceCode(String referenceCode);
}
