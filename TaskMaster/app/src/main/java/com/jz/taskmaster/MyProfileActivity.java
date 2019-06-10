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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class MyProfileActivity extends AppCompatActivity {

    //firebase
    FirebaseFirestore database;
    FirebaseUser user;
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
        setUI();
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

    //add new user info
    public void onAddBioClick(View view) {
        displayName = findViewById(R.id.text_name);
        bio = findViewById(R.id.text_bio);

        ProjectUser projectUser = new ProjectUser();
        projectUser.setDisplayName(displayName.getText().toString());
        projectUser.setBio(bio.getText().toString());

        database.collection("projectUsers")
                .add(projectUser)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("User Info", "Successfully Added" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("User Info", "Update Had Failed", e);
                    }
                });
    }
}
