package com.example.uts_tam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    private ImageButton btnPlanner, btnNotes;
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlanner = findViewById(R.id.btnPlanner);
        btnNotes = findViewById(R.id.btnNotes);

        if(Build.VERSION.SDK_INT>22){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET, Manifest.permission.READ_CONTACTS},EXTERNAL_STORAGE_PERMISSION_CODE);
        }

        btnPlanner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent plannerIntent = new Intent(MainActivity.this, Planner.class);
                startActivity(plannerIntent);
            }
        });
        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notesIntent = new Intent(MainActivity.this, Noter.class);
                startActivity(notesIntent);
            }
        });


    }
}