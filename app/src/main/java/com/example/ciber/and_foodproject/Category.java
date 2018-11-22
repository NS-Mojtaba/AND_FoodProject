package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private Button back;
    private ViewPager viewPager;
    private SwipeAdapter swipeAdapter;
    private Dish [] dishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Intent i = getIntent();
        dishList =  i.getParcelableArrayListExtra("CategoryName");




        viewPager = (ViewPager)findViewById(R.id.view_pager);
        if(dishList.size()>0) {
            swipeAdapter = new SwipeAdapter(this, dishList);
            viewPager.setAdapter(swipeAdapter);
        }
        back = findViewById(R.id.backbtnNew);


        //swipeAdapter.setNameArray();
        //swipeAdapter.setImageArray();
        //swipeAdapter.setDescriptionArray();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        //dishes = MainActivity.getInstance()
    }

}
