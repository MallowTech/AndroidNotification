package com.example.pangaj.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private int mId = 1;
    Button btnSimpleNotification, btnExapndedNotification, btnUpdateNotification, btnProgressNotification, btnDirectReply, btnActionNotification;
    NotificationManager mNotificationManager;
    NotificationCompat.Builder mBuilder;
    EditText etMessage;
    int numMessages;
    String currentText;
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    String replyLabel = "Reply";
    RemoteInput remoteInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimpleNotification = (Button) findViewById(R.id.btn_simple_notification);
        btnExapndedNotification = (Button) findViewById(R.id.btn_expanded_notification);
        btnUpdateNotification = (Button) findViewById(R.id.btn_update_notification);
        btnActionNotification = (Button) findViewById(R.id.btn_action_notification);
        btnProgressNotification = (Button) findViewById(R.id.btn_progress);
        btnDirectReply = (Button) findViewById(R.id.btn_direct_reply);
        etMessage = (EditText) findViewById(R.id.etTextMessage);
        currentText = new String();

        btnSimpleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_sms_white_24dp)
                        .setContentTitle("Title")
                        .setAutoCancel(true)
                        .setContentText("Content");
                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(ResultActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(mId, mBuilder.build());
            }
        });

        btnExapndedNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_sms_white_24dp)
                        .setContentTitle("Event tracker")
                        .setAutoCancel(true)
                        .setContentText("Events received");
                NotificationCompat.InboxStyle inboxStyle =
                        new NotificationCompat.InboxStyle();
                String[] events = new String[6];
// Sets a title for the Inbox in expanded layout
                inboxStyle.setBigContentTitle("Event tracker details:");
// Moves events into the expanded layout
                for (int i = 0; i < events.length; i++) {
                    events[i] = i + "";
                    inboxStyle.addLine(events[i]);
                }
// Moves the expanded layout object into the notification object.
                mBuilder.setStyle(inboxStyle);
                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(ResultActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(mId, mBuilder.build());
            }
        });

        btnUpdateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Sets an ID for the notification, so it can be updated
                int notifyID = 1;
                mBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("New Message")
                        .setContentText("You've received new messages.")
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_sms_white_24dp);
                numMessages = 0;
// Start of a loop that processes data and then notifies the user
                currentText = etMessage.getText().toString();
                mBuilder.setContentText(currentText)
                        .setNumber(++numMessages);
                // Because the ID remains unchanged, the existing notification is
                // updated.
                mNotificationManager.notify(
                        notifyID,
                        mBuilder.build());
            }
        });

        btnActionNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(ResultActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                Notification notification = new Notification.Builder(getApplicationContext())
                        // Show controls on lock screen even when user hides sensitive content.
                        .setVisibility(Notification.VISIBILITY_PUBLIC)
                        .setSmallIcon(R.drawable.ic_play_circle_filled_white_white_24dp)
                        // Add media control buttons that invoke intents in your media service
                        .addAction(R.drawable.ic_navigate_before_white_24dp, "Previous", resultPendingIntent) // #0
                        .addAction(R.drawable.ic_pause_white_24dp, "Pause", resultPendingIntent)  // #1
                        .addAction(R.drawable.ic_navigate_next_white_24dp, "Next", resultPendingIntent)     // #2
                        // Apply the media style template
//                        .setStyle(new Notification.MediaStyle()
//                                .setShowActionsInCompactView(1 /* #1: pause button */)
//                                .setMediaSession(mMediaSession.getSessionToken())
                        .setContentTitle("Wonderful music")
                        .setContentText("My Awesome Band")
//                                .setLargeIcon(albumArtBitmap)
                        .build();
                mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(mId, notification);
            }
        });

        btnDirectReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(ResultActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                        .setLabel(replyLabel)
                        .build();
                //Attach the RemoteInput object to an action using addRemoteInput()
                // Create the reply action and add the remote input
                Notification.Action action =
                        new Notification.Action.Builder(R.drawable.ic_reply_white_24dp,
                                "Label", resultPendingIntent)
                                .addRemoteInput(remoteInput)
                                .build();
                //Apply the action to a notification and issue the notification
                // Build the notification and add the action.
                Notification newMessageNotification =
                        new Notification.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.ic_sms_white_24dp)
                                .setContentTitle("Title")
                                .setContentText("Content")
                                .addAction(action)
                                .build();
                // Issue the notification
                mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(mId, newMessageNotification);
            }
        });

        btnProgressNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                mNotificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(getApplicationContext());
                mBuilder.setContentTitle("Picture Download")
                        .setContentText("Download in progress")
//                        .setFullScreenIntent(PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent, 0), true)
                        .setSmallIcon(R.drawable.ic_sms_white_24dp);
                // Start a lengthy operation in a background thread
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                int incr;
                                // Do the "lengthy" operation 20 times
                                for (incr = 0; incr <= 100; incr += 5) {
                                    // Sets the progress indicator to a max value, the
                                    // current completion percentage, and "determinate"
                                    // state
                                    mBuilder.setProgress(100, incr, false);
                                    // Displays the progress bar for the first time.
                                    mNotificationManager.notify(0, mBuilder.build());
                                    // Sleeps the thread, simulating an operation
                                    // that takes time
                                    try {
                                        // Sleep for 5 seconds
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                    }
                                }
                                mNotificationManager.cancel(0);
                                // When the loop is finished, updates the notification
                                mBuilder.setContentText("Download complete")
                                        // Removes the progress bar
                                        .setProgress(0, 0, false);
                                mNotificationManager.notify(mId, mBuilder.build());
                            }
                        }
                        // Starts the thread by calling the run() method in its Runnable
                ).start();
            }
        });
    }
}
