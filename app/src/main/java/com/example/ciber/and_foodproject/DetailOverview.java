package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailOverview extends AppCompatActivity {


    public Button backBtn;
    public Button detailBtn;
    public Button deleteBtn;

    public TextView descriptionTxt;
    public ImageView dishImg;

    public TextView nameTxt;

    public Dish dish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backBtn = findViewById(R.id.backBtn);
        detailBtn = findViewById(R.id.detailBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        descriptionTxt = findViewById(R.id.descriptionTxt);

        nameTxt = findViewById(R.id.nameTxt);



        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailOverview.this, Detail.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailOverview.this, MainActivity.class));
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailOverview.this, Detail.class));
            }
        });
        if(MainActivity.getInstance().getLoginStatus()){
            deleteBtn.setVisibility(View.VISIBLE);
        }else{
            deleteBtn.setVisibility(View.GONE);
        }

        Intent i = getIntent();
        //Dish dish = (Dish)i .getSerializableExtra("Dish");
        int a = 1+1;
    }

    public void SetDish(Dish _dish){
        dish = _dish;
    }


}
