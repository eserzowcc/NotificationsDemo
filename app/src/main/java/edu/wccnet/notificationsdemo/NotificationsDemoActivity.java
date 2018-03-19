package edu.wccnet.notificationsdemo;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NotificationsDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_demo);
    }

    //https://developer.android.com/guide/topics/ui/notifiers/notifications.html
    public void doNotification(View button) {
        NotificationWorker.doBasicNotification(this, "WCC 251", "This is a notification" );
    }

    public void doBundledNotification(View button) {
        NotificationWorker.doHeadsUpNotification(this);
    }

    public void doCustomNotification(View button) {
        NotificationWorker.doCustomNotification(this);
    }

    public void startService(View button) {
        Toast myToast=Toast.makeText(this, "Synching Orders...", Toast.LENGTH_SHORT );
        myToast.show();

        Intent intent = new Intent(this, OrderSynchService.class);
        startService(intent);
    }
}
