package com.example.uts_tam.reminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.example.uts_tam.MainActivity;
import com.example.uts_tam.R;

public class AlarmBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String text = bundle.getString("event");
        String desc = bundle.getString("desc");
        String date = bundle.getString("date") + " " + bundle.getString("time");
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);


        //Click on Notification
        Intent intent1 = new Intent(context, MainActivity.class);

        //Notification Builder
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 1, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");


        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.activity_alarm_broadcast);
//        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        contentView.setTextViewText(R.id.message, text);
        contentView.setTextViewText(R.id.desc, desc);
        contentView.setTextViewText(R.id.date, date);
        mBuilder.setSmallIcon(R.drawable.alarm);
        mBuilder.setSound(soundUri);
        mBuilder.setAutoCancel(true);
        mBuilder.setContent(contentView);
        mBuilder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        Notification notification = mBuilder.build();
        notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL | Notification.DEFAULT_SOUND;
        notificationManager.notify(1,notification);

    }
}