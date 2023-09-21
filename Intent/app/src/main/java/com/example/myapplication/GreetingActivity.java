package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityGreetingBinding;

public class GreetingActivity extends AppCompatActivity {

    ActivityGreetingBinding binding ;
    String fullName, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGreetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        //Intent is passed into
        Intent intent = this.getIntent();
        this.fullName = intent.getStringExtra("fullName");
        this.message = intent.getStringExtra("message");
        binding.tvMessage.setText(this.message);
    }

    private void goBack() {
        this.onBackPressed();
    }

    @Override
    public void finish() {
        //Prepare data intent
        Intent data = new Intent();
        String feedback = "OK, Hello "+this.fullName + ". How are you?";
        data.putExtra("feedback", feedback);

        //Activity finished ok, return the data
        this.setResult(Activity.RESULT_OK,data);
        super.finish();
    }
}