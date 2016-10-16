package com.example.rainbow.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by 123 on 2016/10/16.
 */
public class DynamicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("Rainbow.example.lab4.dynamicreceiver")){
            Bundle bundle = intent.getExtras();
            String input = bundle.getString("input", "default value");

            //获取状态通知栏管理
            NotificationManager manager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            //实例化通知栏构造器
            Notification.Builder builder = new Notification.Builder(context);
            //对builder进行配置
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.mipmap.dynamic);
            builder.setContentTitle("动态广播")
                    .setContentText(input)
                    .setTicker("您有一条新消息")
                    .setLargeIcon(bm)
                    .setSmallIcon(R.mipmap.dynamic)
                    .setAutoCancel(true);
            Intent mIntent = new Intent(context, MainActivity.class);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
            builder.setContentIntent(mPendingIntent);
            //绑定Notification，发送通知请求
            Notification notify = builder.build();
            manager.notify(0, notify);
        }
    }
}
