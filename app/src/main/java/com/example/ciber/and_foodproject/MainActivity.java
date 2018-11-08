package com.example.ciber.and_foodproject;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore fireBase;
    private Button saveBtn;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fireBase = FirebaseFirestore.getInstance();
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

    }
}
