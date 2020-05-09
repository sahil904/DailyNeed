package com.sahil.dailyneed.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sahil.dailyneed.user.activity.MainActivity;
import com.sahil.dailyneed.R;

import java.io.IOException;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    String user_id;
    String unique_id;
    String type;
    String name;
    String image;
    private boolean isRunning;
    // SessionToken sessionToken;
    private static final String TAG = "FCM Service";
    private static int count = 0;
    //SessionManager sessionManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        this.isRunning = false;
       // sessionManager = new SessionManager(this);
        //unique_id = sessionManager.getUserDetails().get("unique_id");
        Log.d(TAG, "Messagfgfytyty " + remoteMessage.toString());
        Map<String, String> data = remoteMessage.getData();
        try {
            final String type = data.get("type");
            Log.e("TAG", "Type= " + type);
            FirebaseInstanceId.getInstance().deleteInstanceId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        user_id = remoteMessage.getData().get("user_id");
        type = remoteMessage.getData().get("type");
        name = remoteMessage.getData().get("name");
        image = remoteMessage.getData().get("image");


        shownotification(remoteMessage.getData().get("title"),
                remoteMessage.getData().get("body"));


        Log.d("fgqqqqqqqfg", "Message data payload: " + remoteMessage.getData());

        Log.d("fgqqqqqqqfg", String.valueOf((remoteMessage.getData())));

    }

    public void shownotification(final String title, final String message) {

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user_id", unique_id);
        intent.putExtra("friend_id", user_id);
        intent.putExtra("friend_name", title);
        intent.putExtra("friend_image", image);
        intent.putExtra("user_type", type);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel("Sesame", "Sesame", importance);
            mChannel.setDescription(message);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            mNotifyManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "Seasame");
        mBuilder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setColor(Color.parseColor("#FFD600"))
                .setContentIntent(pendingIntent)
                .setChannelId("Sesame")
                .setPriority(NotificationCompat.PRIORITY_LOW);

        mNotifyManager.notify(count, mBuilder.build());
        count++;


    }
}
