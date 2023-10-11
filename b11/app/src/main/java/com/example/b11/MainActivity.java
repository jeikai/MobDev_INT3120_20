package com.example.b11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Intent chứa cả activity và service
        intent = new Intent(MainActivity.this, DemoService2.class);
    }
    public void demo_startService(View view) {
        intent.putExtra("data", "demo data chay ngam Service");
        // Service bắt đầu được khởi chạy
        startService(intent);
    }
    public void demo_stopService(View view) {
        stopService(intent);
    }
}