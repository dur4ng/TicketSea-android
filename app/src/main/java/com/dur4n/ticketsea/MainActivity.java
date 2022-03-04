package com.dur4n.ticketsea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.receiver.TodayEventReminderReceiver;
import com.dur4n.ticketsea.service.EventChecker;
import com.dur4n.ticketsea.service.TodayEventReminderWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    WorkManager workManager;

    TodayEventReminderReceiver reminderReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workManager = WorkManager.getInstance(this);
        //request permissions
        requestPermission();

        // start jobs
        startJobs();

        // receiver
        BroadcastReceiver todayEventReminderReceiver = new TodayEventReminderReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(todayEventReminderReceiver, new IntentFilter("com.dur4n.broadcast.EVENT_IS_LIVE_NOTIFICATION"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
    }



    public boolean hasWritePermission(){
        return (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }
    public boolean hasBindJobPermission(){
        //return (ActivityCompat.checkSelfPermission(this, Manifest.permission.) == PackageManager.PERMISSION_GRANTED);
        return false;
    }
    public void requestPermission(){
        List<String> persmissionToRequest = new ArrayList<>();
        if(!hasWritePermission()){
            persmissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if(!persmissionToRequest.isEmpty()){
            ActivityCompat.requestPermissions(this, persmissionToRequest.toArray(new String[0]), 0);
        }
    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 0 && grantResults.length > 0){
            for (int i: grantResults) {
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("PermissionRequest", permissions.toString() + "GRANTED!!!");
                }else {

                }
            }
        }
    }

    // Start job
    public void startJobs(){
        //startEventCheckerJob();
        //startTestJob();

        //WorkManger
        Constraints constraintsWork = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build();
        //OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(TodayEventReminderWork.class).setConstraints(constraintsWork).build();
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(TodayEventReminderWork.class, 300, TimeUnit.MINUTES)
                .addTag("Test")
                .setConstraints(constraintsWork)
                .build();
        // Para saber si se ha modificado el estado del trabajo que he solicitado a la clase NotificationWork

        // añadimos el trabajo al workManager
        try {
            workManager.enqueueUniquePeriodicWork(
                    "Test",
                    ExistingPeriodicWorkPolicy.KEEP,
                    request
            );
        } catch (Exception e){
            Log.d("workmanagerSetup", "error");
        }
        workManager.getWorkInfoByIdLiveData(request.getId()).observe(this, observadorWorkInfo -> {
            if (observadorWorkInfo != null){
                WorkInfo.State state = observadorWorkInfo.getState();
                Log.d(TAG, "running workManager "+ state);
                //binding.tvResult.append(state.toString()+"\n");
            }
        });
    }

    public void startEventCheckerJob(){
        ComponentName componentName = new ComponentName(this, EventChecker.class);
        JobInfo jobInfo = new JobInfo.Builder(0, componentName)
                .setRequiresBatteryNotLow(true)
                .setPeriodic(2000)
                .setPersisted(true)
                .build();
        //if(JobSchedulerRunner.eventCheckerSchedule();)
    }
    public void startTestJob(){
        ComponentName componentName = new ComponentName(this, EventChecker.class);
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setRequiresBatteryNotLow(true)
                .setPeriodic(2000)
                .setPersisted(true)
                .build();
        //if(JobSchedulerRunner.eventCheckerSchedule();)
    }

}