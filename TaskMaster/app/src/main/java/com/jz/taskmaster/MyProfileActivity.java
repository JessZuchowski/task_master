package com.jz.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MyProfileActivity extends AppCompatActivity {

    //firebase
    FirebaseFirestore database;
    FirebaseUser user;
    String userId;
    String token;
    Context context;

    //add user info
    EditText displayName;
    EditText bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        //get firestore instance
        database = FirebaseFirestore.getInstance();
        //set firestore settings
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        database.setFirestoreSettings(settings);

        context = this;

        //user profile
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
        }

        userId = user.getUid();
        setUI();
        displayName = findViewById(R.id.text_name);
        bio = findViewById(R.id.text_bio);

        findDeviceId();
    }

    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //display user info
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

    public void findDeviceId() {
        FirebaseInstanceId instanceId = FirebaseInstanceId.getInstance();
        instanceId.getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.e("ProfileActivity", "was unsuccessful");
                    return;
                }
                token = task.getResult().getToken();
            }
        });
    }


    //add new user info
    public void onAddBioClick(View view) {

        ProjectUser projectUser = new ProjectUser();
        projectUser.setDisplayName(displayName.getText().toString());
        projectUser.setBio(bio.getText().toString());
        projectUser.getDeviceIds().add(token);

        database.collection("projectUsers")
                .document(userId)
                .set(projectUser)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Log.e("ProfileActivity", "was unsuccessful");
                            return;
                        }
                    }
                });
    }
}
