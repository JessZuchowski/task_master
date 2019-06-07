package com.jz.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyProfileActivity extends AppCompatActivity {

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        setUI();
    }

    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setUI() {
        TextView text1 = findViewById(R.id.text_content);
        TextView text2 = findViewById(R.id.text_content2);
        if (user != null) {
            text1.setText("My Profile: " + user.getDisplayName());
            text2.setText(user.getEmail());
        }
        else {
            text1.setText("");
            text2.setText("");
        }
    }
}
