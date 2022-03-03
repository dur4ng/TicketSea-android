package com.dur4n.ticketsea.receiver;

import static com.dur4n.ticketsea.jobScheduler.JobScheduler.eventCheckerSchedule;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "boot complete, startating jobScheduler", Toast.LENGTH_SHORT).show();
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

