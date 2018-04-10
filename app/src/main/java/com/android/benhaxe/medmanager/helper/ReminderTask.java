package com.android.benhaxe.medmanager.helper;

import android.content.Context;

import com.android.benhaxe.medmanager.utilities.NotificationUtils;

/**
 * Created by Benjamin Masebinu on 06-Apr-18.
 * Lead Organizer GDG FUTA
 * Android & CMS Developer at Planet Nest
 * haxeboom@gmail.com
 */

public class ReminderTask {

    /**
     *This class defines all tasks that will run in the background for this application
     * */

    public static final String ACTION_MEDICATION_REMINDER = "medication-reminder";

    public static void executeTask(Context context, String action){
        if(ACTION_MEDICATION_REMINDER.equals(action)){
            issueMedicationReminder(context);
        }
    }

    private static void issueMedicationReminder(Context context) {
        NotificationUtils.remindUserToTakeDrugs(context);
    }
}
