package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    public Button backBtn;
    public Button saveBtn;

    public EditText descriptionTxt;
    public ImageView dishImg;

    public TextView nameTxt;

    public Dish dish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        backBtn = findViewById(R.id.backBtn);
        saveBtn = findViewById(R.id.saveBtn);
        descriptionTxt = findViewById(R.id.editDecriptionTxt);

        nameTxt = findViewById(R.id.nameTxt);

        Intent i = getIntent();
        Dish newdish = (Dish)i .getParcelableExtra("Dish");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

}
