package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Button signIn_out;
    private Button button_addItem;
    private static MainActivity instance;
    private boolean hasSignedIn;
    private SearchView simpleSearchView;
    private FirebaseFirestore firebase;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        button_addItem = findViewById(R.id.button_addItem);
        button_addItem.setVisibility(View.GONE);
        signIn_out = findViewById(R.id.button_signin_signout);
        signIn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, Login.class));
            }
        }) ;

        firebase = FirebaseFirestore.getInstance();

        simpleSearchView = findViewById(R.id.simpleSearchView);
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebase.collection("food").whereEqualTo("name",query).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(Task<QuerySnapshot> task) {
                                if (task.isSuccessful()&& task.getResult()!= null && !task.getResult().isEmpty()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        Dish dish = new Dish(document.get("category").toString(),document.get("name").toString(),document.get("description").toString());
                                        Intent detailsOverview = new Intent(MainActivity.this, DetailOverview.class);

                                        detailsOverview.putExtra("Dish",dish);
                                        startActivity(detailsOverview);

                                        /*if(document.get("email").toString().equals(email)){

                                        }
                                        else{
                                        }
                                        */
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // do something when text changes
                return false;
            }
        });

        /*fireBase = FirebaseFirestore.getInstance();
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
        */

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(hasSignedIn){
            signIn_out.setOnClickListener(null);
            signIn_out.setText("Sign-out");
            signIn_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hasSignedIn = false;
                    button_addItem.setVisibility(View.GONE);
                    signIn_out.setText("Sign-in");
                    signIn_out.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          startActivity(new Intent(MainActivity.this, Login.class));
                                                      }
                                                  });
                    Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();

                }
            }) ;
            button_addItem.setVisibility(View.VISIBLE);
        }
        else {
            signIn_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Login.class));
                }
            }) ;
            button_addItem.setVisibility(View.GONE);
        }
    }


    public static MainActivity getInstance(){
        return instance;
    }

    public void setSignIn(boolean option){
        hasSignedIn = option;
    }

    public boolean getLoginStatus(){
        return hasSignedIn;
    }

    public void test(){

    }
}
