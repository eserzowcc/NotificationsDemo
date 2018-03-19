package edu.wccnet.notificationsdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.RemoteViews;

/**
 * I would not normally name this class a "worker", but so as not to confuse an Android Service with
 * naming of classes as *Service... Renaming it NotificationWorker : the purpose is the same.  This
 * class is responsible for performing the work around creating Notifications.
 */

public class NotificationWorker {
    private static final String channelDefaultID = "default";

    public static void doBasicNotification(Context context, String title, String message) {
        NotificationCompat.Builder notification = createNotificationBuider(context, title, message);
        doNotification(context, notification.build(), 0);
    }


    private static Intent getMessageReplyIntent(String label) {
        return new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction("REPLY")
                .putExtra("TEST", label);
    }

    // https://developer.android.com/guide/topics/ui/notifiers/notifications.html
    public static void doHeadsUpNotification(Context context) {
        Intent callWCCIntent = new Intent(Intent.ACTION_DIAL);
        callWCCIntent.setData(Uri.parse("tel:7349733300"));

        PendingIntent callWCC = PendingIntent.getActivity(context, 1,
                callWCCIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action replyAction =
                new NotificationCompat.Action.Builder(android.R.drawable.sym_action_call,
                        "CALL WCC", callWCC)
                        .build();

        NotificationCompat.Builder notificationBuider = createNotificationBuider(
                context, "Phone a friend", "This is a heads up notification, I suggest calling WCC");
        notificationBuider.setPriority(Notification.PRIORITY_HIGH).setVibrate(new long[0]);
        notificationBuider.addAction(replyAction);

        doNotification(context, notificationBuider.build(), 0);
    }

    // The coolest of notifications - one which has it's own layout
    public static void doCustomNotification(Context context) {
        String channel_id = "channel_id";
/*
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel(channel_id, "my_channel", importance);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_custom_content);
        remoteViews.setImageViewResource(R.id.image_icon, R.drawable.wcc_small_icon);
        remoteViews.setTextViewText(R.id.text_title, "Custom!");
        remoteViews.setTextViewText(R.id.text_message, "This is a custom notification");
        remoteViews.setImageViewResource(R.id.image_end, R.drawable.wcc_small_icon);

        Notification.Builder builder = new Notification.Builder(context, channel_id)
                .setSmallIcon(R.drawable.wcc_small_icon)
                .setAutoCancel(true);
        builder.setCustomContentView(remoteViews).setStyle(new Notification.DecoratedCustomViewStyle());

        doNotification(context, builder.build(), 0);
        */
    }

    private static NotificationCompat.Builder createNotificationBuider(Context context,
                                                                       String title, String message) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.wcc_icon);
        NotificationCompat.Builder myNotification = new NotificationCompat.Builder(context, channelDefaultID);

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
