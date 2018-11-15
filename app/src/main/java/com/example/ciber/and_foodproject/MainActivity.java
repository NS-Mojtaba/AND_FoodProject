package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button signIn_out;
    private Button button_addItem;
    private static MainActivity instance;
    private boolean hasSignedIn;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        button_addItem = findViewById(R.id.button_addItem);
        button_addItem.setVisibility(View.GONE);
        signIn_out = findViewById(R.id.button_signin_signout);
        signIn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, Login.class));
            }
        }) ;


        /*fireBase = FirebaseFirestore.getInstance();
        saveBtn = findViewById(R.id.saveBtn);
        text =  findViewById(R.id.inputText);



        saveBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String txt = text.getText().toString();

                Map<String, String> map = new HashMap<>();
                map.put("name", txt);
                fireBase.collection("first").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "CREATED",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        */

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(hasSignedIn){
            signIn_out.setOnClickListener(null);
            signIn_out.setText("Sign-out");
            signIn_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hasSignedIn = false;
                    button_addItem.setVisibility(View.GONE);
                    signIn_out.setText("Sign-in");
                    signIn_out.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          startActivity(new Intent(MainActivity.this, Login.class));
                                                      }
                                                  });
                    Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();

                }
            }) ;
            button_addItem.setVisibility(View.VISIBLE);
        }
        else {
            signIn_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Login.class));
                }
            }) ;
            button_addItem.setVisibility(View.GONE);
        }
    }

    public static MainActivity getInstance(){
        return instance;
    }

    public void setSignIn(boolean option){
        hasSignedIn = option;
    }
}
