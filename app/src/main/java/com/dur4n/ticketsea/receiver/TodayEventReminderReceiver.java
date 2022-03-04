package com.dur4n.ticketsea.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.navigation.NavDeepLinkBuilder;

import com.dur4n.ticketsea.R;
import com.dur4n.ticketsea.data.model.Ticket;

import java.util.Random;

public class TodayEventReminderReceiver extends BroadcastReceiver {

    private static final String TAG = "TodayEventReminderReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String message = bundle.getString("EventName");
        Log.d(TAG, message);
        showNoti(context, message);
    }

    public void showNoti(Context context, String eventName){
        //Ejemplo de notificacion (hay que crear el canal de notificacion antes de esto)
        //1- Crear un bundle para pasarle un objeto a la notificacion
        Bundle bundle = new Bundle();
        bundle.putSerializable(Ticket.TAG,  eventName);
        //2- Crear un pendingIntent y pasarle el grafo de navegacion, el destino, el argument...
        PendingIntent pendingIntent = new NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.bottomNavigationFragment)
                .setArguments(bundle)
                .createPendingIntent();

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, "Notifications")
                    .setSmallIcon(R.drawable.ic_action_ticket)
                    .setContentTitle("Event Notification")
                    .setContentText("Today is: " + eventName + "event!!!")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
        }

        //4- Añadir la notificacion al notificationManager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(1000), builder.build());
    }
}
