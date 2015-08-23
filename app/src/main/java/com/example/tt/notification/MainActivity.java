package com.example.tt.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
    Button btnShow,btnCan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = (Button) findViewById(R.id.show);
        btnCan = (Button) findViewById(R.id.off);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Text Notification";
                Intent intent = new Intent(MainActivity.this, NofitiReceiver.class);

                PendingIntent pdIntent = PendingIntent.getActivity(MainActivity.this,  (int) System.currentTimeMillis(), intent, 0);

                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Notification notification = new Notification.Builder(MainActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification Title")
                        .setContentText(msg)
                        .setAutoCancel(true)
                        .setSound(soundUri)
                        .setContentIntent(pdIntent)
                        .addAction(R.mipmap.ic_launcher, "Open", pdIntent)
                        .addAction(0,"Remind",pdIntent)
                        .build();

                NotificationManager notificationManager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // hide the notification after its selected
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(0,notification);
            }
        });
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Context.NOTIFICATION_SERVICE != null) {
                    String ns = Context.NOTIFICATION_SERVICE;
                    NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(ns);
                    notificationManager.cancel(0);

                }
            }
        });
    }


}
