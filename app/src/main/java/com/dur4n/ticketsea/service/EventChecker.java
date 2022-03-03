package com.dur4n.ticketsea.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.dur4n.ticketsea.jobScheduler.JobScheduler;
import com.dur4n.ticketsea.receiver.BootReceiver;

public class EventChecker extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(this.getClass().getSimpleName(),"onStartJob");
        Handler mHandler = new Handler(getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Checking todays events", Toast.LENGTH_SHORT).show();
                jobFinished(jobParameters, false);
            }
        });

        JobScheduler.eventCheckerSchedule(getApplicationContext());

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    //TODO
    public boolean checkTodaysEvents(){
        return false;
    }
}
