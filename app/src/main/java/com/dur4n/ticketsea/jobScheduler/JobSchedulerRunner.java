package com.dur4n.ticketsea.jobScheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import com.dur4n.ticketsea.service.EventChecker;

public class JobSchedulerRunner {
    private static final int PERIOD_MS = 10000;
    public static void eventCheckerSchedule(Context context) {
        ComponentName serviceComponent = new ComponentName(context, EventChecker.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(15*60 * 1000); // Wait at least 30s
        builder.setOverrideDeadline(30*60 * 1000); // Maximum delay 60s

        JobScheduler jobScheduler = (JobScheduler)context.getSystemService(context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
}
