package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Detail extends AppCompatActivity {

    public Button backBtn;
    public Button saveBtn;

    public EditText descriptionTxt;
    public ImageView dishImg;

    public TextView nameTxt;

    public Dish dish;
    private FirebaseFirestore firebase;
    private static final String KEY_DESCRIPTION ="description";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        backBtn = findViewById(R.id.backBtn);
        saveBtn = findViewById(R.id.saveBtn);
        descriptionTxt = findViewById(R.id.editDecriptionTxt);

        nameTxt = findViewById(R.id.nameTxt);
        firebase = FirebaseFirestore.getInstance();
        Intent i = getIntent();
        Dish newdish = (Dish)i .getParcelableExtra("Dish");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDescription(dish);
                finish();
            }
        });
        SetDish(newdish);

        nameTxt.setText(dish.name);
        descriptionTxt.setText(dish.description);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if(MainActivity.getInstance().getLoginStatus()){
            saveBtn.setVisibility(View.VISIBLE);
        }else{
            saveBtn.setVisibility(View.GONE);
        }


    }




    public void SetDish(Dish _dish){
        dish = _dish;
    }

    public  void updateDescription(final Dish _dish){
        final String description = descriptionTxt.getText().toString();
        firebase.collection("food").whereEqualTo("name",_dish.name).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                // here you can get the id.
                                _dish.description = description;
                                firebase.collection("food").document(documentSnapshot.getId()).update(KEY_DESCRIPTION,description);
                                DetailOverview.getInstance().SetDish(_dish);

                                Toast.makeText(Detail.this,"Dish description updated", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Detail.this,"Dish description was not updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}