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
    boolean flag = false;

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
                    IntentFilter dynamic_filter = new IntentFilter();
                    dynamic_filter.addAction("Rainbow.example.lab4.dynamicreceiver");
                    registerReceiver(dynamicReceiver, dynamic_filter);
                    flag = true;
                    registerButton.setText("Unregister Broadcast");
                }
                else{
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
                Bundle bundle = new Bundle();
                bundle.putString("input", text.getText().toString());
                Intent intent = new Intent("Rainbow.example.lab4.dynamicreceiver");
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });


    }
}
