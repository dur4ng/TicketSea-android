package com.dur4n.ticketsea.service;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import android.content.Intent;
import android.util.Log;

import com.dur4n.ticketsea.data.repository.ticket.TicketLocalRepository;

import java.util.HashMap;

public class TodayEventReminderWork extends Worker {

    private static final String TAG = TodayEventReminderWork.class.getSimpleName();

    public TodayEventReminderWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //Context appContext = getApplicationContext();
        Log.d(TAG, "Starting...");

        //test();
        checkTodaysEvents();
        return Result.success();

    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.d(TAG, "Stopping...");
    }

    // send a broadcast with a intent with ta event name
    public void setReminder(String eventName) {
        Log.d(TAG, "starting sending intent");
        Intent intent = new Intent(getApplicationContext(), TodayEventReminderWork.class);
        intent.setAction("com.dur4n.broadcast.EVENT_IS_LIVE_NOTIFICATION");
        intent.putExtra("EventName",eventName);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        getApplicationContext().sendBroadcast(intent);
        Log.d(TAG, "intent sended");
    }

    public void test(){

        setReminder("blackHat 2022");
    }

    // checks live events
    public void checkTodaysEvents(){
        // get list todays events
        HashMap<Integer, String> eventsToNotificate = TicketLocalRepository.getInstance().checkTodaysEvents();
        if(eventsToNotificate.isEmpty()){

        } else {
            // create a notification with each event
            eventsToNotificate.forEach((id, eventName)->{
                setReminder(eventName);
            });
        }
    }
}
