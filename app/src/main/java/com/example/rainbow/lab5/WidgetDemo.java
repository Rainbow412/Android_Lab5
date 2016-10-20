package com.example.rainbow.lab5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetDemo extends AppWidgetProvider {
    private String STATICACTION = "Rainbow.example.lab5.staticreceiver";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Intent clickInt = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, clickInt, 0);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
        //先初始化文字为Widget，图片为苹果
        rv.setTextViewText(R.id.appwidget_text, "Widget");
        rv.setImageViewResource(R.id.appwidget_image, R.mipmap.apple);
        //点击图片打开MainActivity
        rv.setOnClickPendingIntent(R.id.appwidget_image, pi);
        //更新widget
        appWidgetManager.updateAppWidget(appWidgetIds, rv);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
        Bundle bundle = intent.getExtras();

        if (intent.getAction().equals(STATICACTION)) {
            String fruitName = bundle.getString("fruitName");
            int img = bundle.getInt("img");
            //根据接收的内容设置文字和图片
            rv.setTextViewText(R.id.appwidget_text, fruitName);
            rv.setImageViewResource(R.id.appwidget_image, img);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), WidgetDemo.class.getName());
            //更新widget
            appWidgetManager.updateAppWidget(thisAppWidget, rv);

        }
    }

}