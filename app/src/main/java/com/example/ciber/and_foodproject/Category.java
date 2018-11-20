package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.CREATOR;
import static android.content.ContentValues.TAG;
import static com.example.ciber.and_foodproject.MainActivity.convert;

public class Category extends AppCompatActivity {
    private ArrayList<Dish> dishList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Intent i = getIntent();
        dishList =  i.getParcelableArrayListExtra("CategoryName");

        int x = dishList.size();

    }
}
