package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteDish extends AppCompatActivity {

    private FirebaseFirestore firebase;
    public Button noBtn;
    public Button yesBtn;

    public TextView deleteTxt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebase = FirebaseFirestore.getInstance();
        noBtn = findViewById(R.id.noBtn);
        yesBtn = findViewById(R.id.yesBtn);
        deleteTxt = findViewById(R.id.deleteTxt);



        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteDish.this, DetailOverview.class));
            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}
