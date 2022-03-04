package com.dur4n.ticketsea.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.dur4n.ticketsea.data.repository.ticket.TicketLocalRepository;
import com.dur4n.ticketsea.jobScheduler.JobSchedulerRunner;

import java.util.HashMap;

public class EventChecker extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(this.getClass().getSimpleName(),"onStartJob");

        Handler mHandler = new Handler(getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Checking todays events", Toast.LENGTH_SHORT).show();
                //checkTodaysEvents();
                test();
                jobFinished(jobParameters, false);
            }
        });

        JobSchedulerRunner.eventCheckerSchedule(getApplicationContext());

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
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

    // send a broadcast with a intent with ta event name
    public void setReminder(String eventName) {
        Intent intent = new Intent(this, EventChecker.class);
        intent.setAction("com.dur4n.broadcast.EVENT_IS_LIVE_NOTIFICATION");
        intent.putExtra("EventName",eventName);
        sendBroadcast(intent);


    }

    public void test(){
        setReminder("blackHat 2022");
    }
}
