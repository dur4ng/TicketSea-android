package com.dur4n.ticketsea;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.dur4n.ticketsea.data.room.LocalDB;

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
    }
}
