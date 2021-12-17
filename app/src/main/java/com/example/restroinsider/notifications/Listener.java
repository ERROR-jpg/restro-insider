package com.example.restroinsider.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.restroinsider.R;
import com.example.restroinsider.order_status;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Listener {
    private static String TAG = "Listener";

    public static void notifyx(Context context, Object msg , boolean push) {
        try {
            HashMap<String, Object> x = (HashMap<String, Object>) msg;
            Log.d(TAG, "notifyx: "+x);
            if(push){
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("notify");
                reference.child("items").setValue(x);
                reference.removeValue();
            }
            List<String> keys = new ArrayList<>(x.keySet());
            StringBuilder sb = new StringBuilder();
            for (String xx : keys) {
                sb.append(xx).append(" : ").append(x.get(xx)).append("\n");
            }
            Intent notificationIntent = new Intent(context, order_status.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "CHANNEL_ID")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Order Complete")
                    .setContentIntent(pendingIntent)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(sb.toString()))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat nm = NotificationManagerCompat.from(context);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = "CHANNEL_ID";
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Order Complete",
                        NotificationManager.IMPORTANCE_HIGH);
                nm.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }

            nm.notify(1, builder.build());

            SharedPreferences sp = context.getSharedPreferences("ordersKey", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            Object oj;
            oj = sp.getString("ordersKey", new HashMap<String, Object>().toString());
            HashMap<String, Object> xy = (HashMap<String, Object>) oj;
            xy.putAll(x);
            editor.putString("ordersKey", xy.toString());

        } catch (Exception e) {
            Log.e(TAG, "notifyx: " + e);
        }
    }
}
