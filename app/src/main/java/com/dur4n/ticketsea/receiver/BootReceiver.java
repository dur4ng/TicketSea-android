package com.dur4n.ticketsea.receiver;

import static com.dur4n.ticketsea.jobScheduler.JobSchedulerRunner.eventCheckerSchedule;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    String TAG = "BootReceiver Custom";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"boot complete, startating jobScheduler");
        // Start the eventScheduler
        eventCheckerSchedule(context);
        /*
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Intent newIntent = new Intent(context, EventChecker.class);
            PendingIntent pendingIntent = PendingIntent.getService(
                    context,
                    1,
                    newIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            manager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis(), PERIOD_MS,
                    pendingIntent);
        } else {
            eventCheckerSchedule(context);

    }

         */
    }
}

