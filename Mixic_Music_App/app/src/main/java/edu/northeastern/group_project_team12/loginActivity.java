package edu.northeastern.group_project_team12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.util.Map;

public class loginActivity extends AppCompatActivity {
    SharedPreferences globalLoginData;
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        globalLoginData = getSharedPreferences("login", MODE_PRIVATE);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }

    public void login(View view) {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
        db.child(username).get().addOnCompleteListener((t) -> {
            Map<String, Object> userMap = (Map<String, Object>) t.getResult().getValue();
            User user;
            if (userMap == null) {
                user = new User(username, Instant.now().getEpochSecond());
            } else {
                user = new User(username, (long) userMap.get("lastVisitedEpochSecond"));
            }
            db.child(username).setValue(user);
            globalLoginData.edit().putString("username", user.getUsername()).putLong("lastVisitedEpochSecond", user.getLastVisitedEpochSecond()).apply();
        });
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }
}

