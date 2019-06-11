package com.jz.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //firebase
    FirebaseFirestore database;
   FirebaseUser user;

   Context context;
    TextView text;

   private static final int RC_SIGN_IN = 1717;

   //recycler view
   RecyclerView recyclerView;
   RecyclerView.LayoutManager layoutManager;
   TaskLayoutAdapter adapter;
   public List<ProjectTask> projectTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get Firestore instance
        database = FirebaseFirestore.getInstance();
        //set Firestore settings
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

        //notification listener
        ListenerRegistration registration = database.collection("projectTasks")
                //.whereEqualTo("title", "userId") ???
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                            if (snap.exists()) {
                                ProjectTask projectTask = snap.toObject(ProjectTask.class);
                                //text.setText()(projectTask.getWhateverJavaScriptFunction());
                                System.out.println(snap.getId());
                            }
                        }
                    }
                });

        //recylcer view
        projectTasks = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.task_entry);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TaskLayoutAdapter(projectTasks);
        recyclerView.setAdapter(adapter);
    }

    //log in
    public void onLoginClick(View view) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    //log out
    public void onLogoutClick(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("User", "Logout was Successful");
                    }
                });
        setUI();
    }

    //user interface
    private void setUI() {
        Button login = findViewById(R.id.button_login);
        Button logout = findViewById(R.id.button_logout);
        TextView text = findViewById(R.id.text_content);
        if (user != null) {
            login.setEnabled(false);
            logout.setEnabled(true);
            text.setText("Home: " + user.getDisplayName());
        }
        else {
            login.setEnabled(true);
            logout.setEnabled(false);
            text.setText("");
        }
    }

    //firebase request/response
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RC_SIGN_IN) {

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                Log.d("MAINACTIVITY", "user" + user.getEmail());
            }
            else {
                Log.e("MAINACTIVITY", "Login Failed");
            }
            setUI();
        }
    }

    //get all tasks from database
    public void onGetTaskClick(View view) {
        database.collection("projectTasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot snap = task.getResult();
                            List<ProjectTask> projectTasks = new ArrayList<>();
                            for (DocumentSnapshot document : snap.getDocuments()) {
                                Log.d("Task", document.getId() + " " + document.getData());
                                ProjectTask pt = document.toObject(ProjectTask.class);
                                pt.setId(document.getId());
                                projectTasks.add(pt);
                            }
                            adapter.setProjectTasks(projectTasks);
                        }
                        else{
                            Log.w("Task", "Error Getting Task", task.getException());
                        }
                    }
                });
    }

    //add device token
    public void onAddDeviceClick(View view) {
        FirebaseInstanceId instanceId = FirebaseInstanceId.getInstance();

        instanceId
                .getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if ( !task.isSuccessful()) {
                            Log.w("NOTIFICATION", ": Failed to get Instance", task.getException());
                            return;
                        }

                        String token = task.getResult().getToken();
                        Log.d("NOTIFICATION", token);
                        text.setText(token);
                    }
                });
    }

    public void onMyTaskButtonClick(View view) {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

    public void onMyProfileButtonClick(View view) {
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }

    public void onAddTaskClick(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }
}
