package com.dur4n.ticketsea.jobScheduler;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;

import com.dur4n.ticketsea.service.EventChecker;

public class JobScheduler {
    private static final int PERIOD_MS = 300000;
    public static void eventCheckerSchedule(Context context) {
        ComponentName serviceComponent = new ComponentName(context, EventChecker.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setPeriodic(PERIOD_MS);
        builder.setMinimumLatency(PERIOD_MS);
        builder.setOverrideDeadline(PERIOD_MS);
        android.app.job.JobScheduler jobScheduler = (android.app.job.JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
}
