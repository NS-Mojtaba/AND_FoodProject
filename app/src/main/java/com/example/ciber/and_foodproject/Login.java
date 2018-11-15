package com.example.ciber.and_foodproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class Login extends Activity {
    private FirebaseFirestore firebase;
    private Button button_logIn;
    private EditText user_email,user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_window);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.7),(int)(height*0.7));

        button_logIn = findViewById(R.id.button_logIn);
        user_email = findViewById(R.id.user_email);
        user_pass = findViewById(R.id.user_pass);

        firebase = FirebaseFirestore.getInstance();
        button_logIn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signID(user_email.getText().toString(),user_pass.getText().toString());
                /*View parent = view.getRootView();
                if (parent != null) {
                    if (parent != null) {
                        Button b = parent.findViewById(R.id.button_addItem);
                        b.setVisibility(View.VISIBLE);
                    }
                }
                */


                /*
                String txt = text.getText().toString();

                Map<String, String> map = new HashMap<>();
                map.put("name", txt);
                fireBase.collection("first").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "CREATED",Toast.LENGTH_LONG).show();
                    }
                });
                */
            }

        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    private void signID(final String email, final String pass) {
        firebase.collection("users").whereEqualTo("email",email).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()&& task.getResult()!= null && !task.getResult().isEmpty()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.get("email").toString().equals(email)){
                                    if(document.get("pass").toString().equals(pass)){
                                        MainActivity.getInstance().setSignIn(true);
                                        finish();
                                        Toast.makeText(Login.this, "Success logging in", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(Login.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(Login.this, "User is not registered", Toast.LENGTH_SHORT).show();
                                }

                            }
                        } else {
                            Toast.makeText(Login.this, "User is not registered", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
