package com.thisischool.chool.OnlineDatabase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.thisischool.chool.Activities.AddWorkBookActivity;
import com.thisischool.chool.Activities.ClassChatGroupActivity;
import com.thisischool.chool.Activities.FriendRequestActivity;
import com.thisischool.chool.Activities.InboxActivity;
import com.thisischool.chool.Activities.WorkBookActivity;
import com.thisischool.chool.Classes.Constants;
import com.thisischool.chool.Models.Notification;
import com.thisischool.chool.R;

import org.json.JSONObject;

import static com.thisischool.chool.Classes.Constants.CLICK_ACTION;
import static com.thisischool.chool.Classes.Constants.TO_CLASS_CHAT_GROUP;
import static com.thisischool.chool.Classes.Constants.TO_FRIEND_REQUEST;
import static com.thisischool.chool.Classes.Constants.TO_INBOX;
import static com.thisischool.chool.OnlineDatabase.MyReferences.notificationsRef;

public class MyNotificationService extends FirebaseMessagingService {
    private static final String TAG = "MyNotificationService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "onMessageReceived: ");
        int click_action = 0;
        if (remoteMessage.getData().size() > 0) {

            try {
                JSONObject object =
                Log.d(TAG, "onMessageReceived Click Action: " + click_action);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (remoteMess1;
            } else if (title.equals("New Message")) {
                click_action = 2;
            }
            showNotification(title, body, click_action);
            saveNotification(title, body, click_action);
        }
    }

    private void saveNotification(String title, String msg, int click_action) {
        DatabaseReference reference = notificationsRef();
        String id = reference.push().getKey();
        if (title.equals("New Friend Request")) {
            click_action = 1;
        } else if (title.equals("New Message")) {
            click_action = 2;
        }
        Notification notification = new Notification(click_action,title,msg,id);
        reference.child(id).setValue(notification).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "saveNotification: ");
            }
        });
    }

    private void showNotification(String title, String message, int click_action) {

        Intent intent;

        switch (click_action)

            case TO_FRIEND_REQUEST:


            case TO_INBOX:
                intent = new Intent(this, InboxActivity.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + click_action);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent
                , PendingIntent.FLAG_ONE_S
                .setContentText(message)
                .setContentIntent(pendingIntent);

        builder.setVibrate(new long[]{2000});

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new Notificat
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(0, builder.build());


    }
}
