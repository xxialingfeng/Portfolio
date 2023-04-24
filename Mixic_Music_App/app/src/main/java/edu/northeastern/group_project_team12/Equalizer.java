package edu.northeastern.group_project_team12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bullhead.equalizer.EqualizerFragment;
import com.bullhead.equalizer.Settings;

public class Equalizer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equalizer);
        Button backButton = findViewById(R.id.back);
        Intent intent = getIntent();
        int sessionId = intent.getIntExtra("data_key", 0);
        Settings.isEditing = true;
        EqualizerFragment equalizerFragment = EqualizerFragment.newBuilder()
                .setAudioSessionId(sessionId)
                .setAccentColor(Color.parseColor("#1A78F2"))
                .build();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.equalizerContainer, equalizerFragment)
                .addToBackStack(null)
                .commit();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecording();
            }
        });
    }

    public void openRecording(){
        Intent intent = new Intent(this, recordFragment.class);
        startActivity(intent);
    }

}