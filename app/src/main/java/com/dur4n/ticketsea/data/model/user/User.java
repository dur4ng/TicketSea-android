package com.dur4n.ticketsea.data.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    public static final String TAG = "USER";
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    String username;
    @NonNull
    String password;

    public User(
            int id,
            @NonNull String username,
            @NonNull String password
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Ignore
    public User(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public static String getTAG() {
        return TAG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
