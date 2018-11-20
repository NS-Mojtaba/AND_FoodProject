package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class DetailOverview extends AppCompatActivity {


    private Button backBtn;
    private Button editBtn;
    private Button deleteBtn;

    private TextView descriptionTxt;
    private ImageView dishImg;
    private static DetailOverview instance;

    private TextView nameTxt;

    private Dish dish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_overview);
        instance = this;


        backBtn = findViewById(R.id.backBtn);
        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        descriptionTxt = findViewById(R.id.descriptionTxt);

        nameTxt = findViewById(R.id.nameTxt);



        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent details = new Intent(DetailOverview.this, Detail.class);

                details.putExtra("Dish",dish);
                startActivity(details);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(DetailOverview.this, MainActivity.class));
                finish();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().deleteDish(dish, v);
                finish();
            }
        });

        Dish newDish = (Dish)getIntent().getParcelableExtra("Dish");


        SetDish(newDish);

        nameTxt.setText(dish.name);
        descriptionTxt.setText(dish.description);



        if(MainActivity.getInstance().getLoginStatus()){
            deleteBtn.setVisibility(View.VISIBLE);
        }else{
            deleteBtn.setVisibility(View.GONE);
        }
    }

    public static DetailOverview getInstance() {
        return instance;
    }
    public void SetDish(Dish _dish){
        dish = _dish;
    }

}
