package com.dur4n.ticketsea;

import android.Manifest;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavDeepLinkBuilder;

import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.room.LocalDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketSeaApplication extends Application {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        // Set database
        LocalDB.create(this);

        // notificatin channel
        NotificationChannel notificationChannel = null;
        notificationChannel = new NotificationChannel("Notifications", "N", NotificationManager.IMPORTANCE_LOW);

        notificationChannel.setDescription("Channel for ticket notifications");
        notificationChannel.setShowBadge(true);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(notificationChannel);

        // service

    }



}
