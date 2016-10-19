package com.example.rainbow.lab4;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 123 on 2016/10/16.
 */
public class DynamicActivity extends AppCompatActivity {
    boolean flag = false; //记录是否注册
    private String DYNAMICACTION = "Rainbow.example.lab4.dynamicreceiver";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_layout);

        final DynamicReceiver dynamicReceiver = new DynamicReceiver();
        final Button registerButton = (Button)findViewById(R.id.dynamicRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==false) {
                    //注册广播
                    IntentFilter dynamic_filter = new IntentFilter();
                    dynamic_filter.addAction(DYNAMICACTION); //添加动态广播的Action
                    registerReceiver(dynamicReceiver, dynamic_filter); //注册自定义动态广播消息
                    flag = true;
                    registerButton.setText("Unregister Broadcast");
                }
                else{
                    //注销广播
                    unregisterReceiver(dynamicReceiver);
                    registerButton.setText("Register Broadcast");
                    flag = false;
                }
            }
        });

        final EditText text = (EditText)findViewById(R.id.dynamicEdit);
        Button send = (Button)findViewById(R.id.dynamicSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送广播，传递相应文字
                Bundle bundle = new Bundle();
                bundle.putString("input", text.getText().toString());
                Intent intent = new Intent(DYNAMICACTION);
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });
    }
}
