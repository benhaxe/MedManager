package com.android.benhaxe.medmanager.sync;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.android.benhaxe.medmanager.helper.ReminderTask;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.JobParameters;
/**
 * Created by Benjamin Masebinu on 31-Mar-18.
 * Lead Organizer GDG FUTA
 * Android & CMS Developer at Planet Nest
 * haxeboom@gmail.com
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MedicationReminderJobService extends JobService{
    /**
     * By default JobService runs on the main thread
     *
     * */
    private static final String TAG = MedicationReminderJobService.class.getSimpleName();

    private static AsyncTask mBackgroundTask;

    //onStartJob - Execute the task that send out the notification that we have made
    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        // Make it run in the background
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = MedicationReminderJobService.this;
                ReminderTask.executeTask(context, ReminderTask.ACTION_MEDICATION_REMINDER);
                return null;
            }

            // Job services tells the system when the job is done by calling jobFinished
            @Override
            protected void onPostExecute(Object o) {
                jobFinished(jobParameters, false);
            }
        };
        mBackgroundTask.execute();
        return true;
    }

    //onStop - It get called when the requirement is no more met
    @Override
    public boolean onStopJob(JobParameters job) {
        if (mBackgroundTask != null)mBackgroundTask.cancel(true);
        //returning true here - means as soon as the condition/requirement of the job is met it should retry iy
        return true;
    }
}
