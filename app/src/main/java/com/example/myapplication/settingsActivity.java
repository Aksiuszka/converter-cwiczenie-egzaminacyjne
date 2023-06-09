package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class settingsActivity extends AppCompatActivity {

    private Switch decision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        decision = findViewById(R.id.switch1);
        decision.setChecked(readPreferences());

        decision.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences pref = getSharedPreferences("Set", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("theme", decision.isChecked());
                editor.apply();
            }
        });
        }
        Boolean readPreferences(){
        SharedPreferences prefs = getSharedPreferences("Set", MODE_PRIVATE);
        return prefs.getBoolean("theme", false);
        }
    }
