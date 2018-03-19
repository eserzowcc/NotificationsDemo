package edu.wccnet.notificationsdemo;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by venus on 3/19/18.
 */

public class OrderSynchService extends IntentService {
    public OrderSynchService() {
        super("OrderSynchService" );
    }


    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        try {
            Thread.sleep(10000);
            NotificationWorker.doBasicNotification(this, "Complete", "Finished with work!" );
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }
}
