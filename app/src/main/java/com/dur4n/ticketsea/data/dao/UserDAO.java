package com.dur4n.ticketsea.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dur4n.ticketsea.data.model.user.User;

@Dao
public interface UserDAO {
    @Insert
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user WHERE username=:username AND password=:password")
    User validUser(String username, String password);

    @Query("SELECT * FROM user WHERE username=:username")
    User checkUsername(String username);
}
