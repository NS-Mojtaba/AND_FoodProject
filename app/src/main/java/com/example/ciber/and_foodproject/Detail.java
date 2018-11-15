package com.example.ciber.and_foodproject;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backBtn = findViewById(R.id.backBtn);
        saveBtn = findViewById(R.id.saveBtn);
        descriptionTxt = findViewById(R.id.editDecriptionTxt);

        nameTxt = findViewById(R.id.nameTxt);

        //Call main activity to check login status

        if(MainActivity.getInstance().getLoginStatus()){
            saveBtn.setVisibility(View.VISIBLE);
        }else{
            saveBtn.setVisibility(View.GONE);
        }


    }

}
