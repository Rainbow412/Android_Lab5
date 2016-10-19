package com.example.rainbow.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

/**
 * Created by 123 on 2016/10/16.
 */
public class StaticReceiver extends BroadcastReceiver{
    private String STATICACTION = "Rainbow.example.lab4.staticreceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(STATICACTION)){
            Bundle bundle = intent.getExtras();
            String fruitName = bundle.getString("fruitName", "default value");
            String imgString = bundle.getString("img", "default value");
            int img = Integer.parseInt(imgString);

            //获取状态通知栏管理
            NotificationManager manager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            //实例化通知栏构造器
            Notification.Builder builder = new Notification.Builder(context);
            //对builder进行配置
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), img);
            builder.setContentTitle("静态广播")
                    .setContentText(fruitName)
                    .setTicker("您有一条新消息")
                    .setLargeIcon(bm)
                    .setSmallIcon(img)
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
