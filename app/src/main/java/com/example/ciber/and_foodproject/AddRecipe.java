package com.example.ciber.and_foodproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class AddRecipe extends AppCompatActivity {
    private Button back;
    private Button save;
    private TextView text_name;
    private TextView text_description;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        back = findViewById(R.id.button_back);
        save = findViewById(R.id.button_save_recipe);
        text_name = findViewById(R.id.text_name);
        text_description = findViewById(R.id.text_description);
        spinner = findViewById(R.id.spinner);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MainActivity.getInstance().searchForDish(text_name.getText().toString())){
                    // SAVE FUNCTIONALITY GOES HERE!!!!!!!!!!!!
                    Map<String, String> map = new HashMap<>();
                    map.put("name", MainActivity.getInstance().convert(text_name.getText().toString()));
                    map.put("description", text_description.getText().toString());
                    map.put("category", spinner.getSelectedItem().toString());
                    MainActivity.getInstance().addRecipe(map, v);

                }
                else{
                    Toast.makeText(AddRecipe.this, "Name already exists", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
