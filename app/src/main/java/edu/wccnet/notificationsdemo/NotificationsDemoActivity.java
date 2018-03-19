package edu.wccnet.notificationsdemo;

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

    public void doStandardNotification(View button) {
        NotificationService.doStandardNotification(this, "WCC 251", "This is a standard notification" );
    }
}
