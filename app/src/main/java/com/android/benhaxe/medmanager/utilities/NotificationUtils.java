package com.android.benhaxe.medmanager.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.android.benhaxe.medmanager.BaseActivity;
import com.android.benhaxe.medmanager.ui.MainActivity;
import com.android.benhaxe.medmanager.R;

import net.alexandroid.shpref.ShPref;

/**
 * Created by Benjamin Masebinu on 04-Apr-18.
 * Lead Organizer GDG FUTA
 * Android & CMS Developer at Planet Nest
 * haxeboom@gmail.com
 */

public class NotificationUtils {
    private static final int DRUGS_REMINDER_PENDING_INTENT_ID = 100;
    private static final int DRUGS_REMINDER_NOTIFICATION_ID = 200;
    private static Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    private static final String NOTIFICATION_CONTENT = ShPref.getString(BaseActivity.DRUGS_NAME)
            + "\n" + ShPref.getString(BaseActivity.DRUGS_DOSAGE);

    //Helper method
    private static PendingIntent pendingIntent(Context context){

        //This method returns the instance of a pending intent - that will be passed to another app (Notification manager)

        Intent startActivityIntent = new Intent(context, MainActivity.class);

        /**
         * context: The context in which the pending intent should start the activity
         * requestCode - int:Private request code for the sender
         * intent - Intent of the activity to be launched
         * flag
         * */

        return PendingIntent.getActivity(
                context,
                DRUGS_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context){
        Resources res = context.getResources();

        return BitmapFactory.decodeResource(res, R.drawable.ic_time);
    }

    public static void remindUserToTakeDrugs(Context context){

        // Notification builder to describe a notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification_small)
                .setLargeIcon(largeIcon(context))
                .setContentTitle("Med Manager")
                .setContentText(NOTIFICATION_CONTENT)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(NOTIFICATION_CONTENT))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSound(alarmSound)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setContentIntent(pendingIntent(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        //Notification Manager to displays a notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(DRUGS_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
    }
}
