package com.example.rainbow.lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button staticButton = (Button)findViewById(R.id.staticButton);
        Button dynamicButton = (Button)findViewById(R.id.dynamicButton);

        staticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStatic = new Intent(MainActivity.this, StaticActivity.class);
                startActivity(intentStatic);
            }
        });

        dynamicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDynamic = new Intent(MainActivity.this, DynamicActivity.class);
                startActivity(intentDynamic);
            }
        });
    }
}
