package edu.wccnet.notificationsdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by venus on 3/19/18.
 */

public class NotificationService {
    private static final String channelDefaultID="default";

    public static void doStandardNotification(Context context, String title, String message ) {
        NotificationCompat.Builder notification = createNotificationBuider(context, title, message );
        doNotification(context, notification.build(), 0);
    }

    private static NotificationCompat.Builder createNotificationBuider(Context context,
                                                               String title, String message) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.wcc_icon);
        NotificationCompat.Builder myNotification= new NotificationCompat.Builder(context, channelDefaultID);

        // You'll need to update the minimum SDK version to see this working properly, you'll also
        // need to run in an emulator that supports at least API level 26
        //CharSequence channelName = "Default Channel";
        //int importance = NotificationManager.IMPORTANCE_LOW;
        //NotificationChannel notificationChannel = new NotificationChannel(channelDefaultID, channelName, importance);
        //notificationChannel.setLightColor(Color.RED);

        myNotification.setSmallIcon(R.drawable.wcc_small_icon)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(largeIcon)
                .setColor(ContextCompat.getColor(context, R.color.white))
                .setAutoCancel(true);

        return myNotification;
    }

    private static void doNotification(Context context, Notification notification, int id) {

        NotificationManager myManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        myManager.notify(id, notification);
    }
}
