package com.example.ciber.and_foodproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Button signIn_out;
    private Button button_addItem;
    private static MainActivity instance;
    private boolean hasSignedIn;
    private SearchView simpleSearchView;
    private FirebaseFirestore firebase;
    private boolean exists;

    private ImageView image_chicken;
    private ImageView image_beef;
    private ImageView image_pork;
    private ImageView image_fish;
    private ImageView image_dessert;
    private ImageView image_vegan;
    //private StorageReference mStorageRef;
    private FirebaseFirestore fireBase;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        fireBase = FirebaseFirestore.getInstance();
        button_addItem = findViewById(R.id.button_addItem);
        button_addItem.setVisibility(View.GONE);

        //******************[Category images and click events]****************************
        image_chicken = findViewById(R.id.image_chicken);
        image_beef = findViewById(R.id.image_beef);
        image_dessert = findViewById(R.id.image_dessert);
        image_pork = findViewById(R.id.image_pork);
        image_fish = findViewById(R.id.image_fish);
        image_vegan = findViewById(R.id.image_vegan);

        image_chicken.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Category.class));
            }
        });
        image_beef.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Category.class));
            }
        });
        image_dessert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Category.class));
            }
        });
        image_pork.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Category.class));
            }
        });
        image_fish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Category.class));
            }
        });
        image_vegan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Category.class));
            }
        });
        //**********************************************

        //mStorageRef = FirebaseStorage.getInstance().getReference();
        //StorageReference tmp = mStorageRef.child("/categories/Chicken.png");
        /*Glide.with(this.getApplicationContext())
                .load(tmp)
                .into(image_chicken);
                */


        button_addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddRecipe.class));
            }
        });
        signIn_out = findViewById(R.id.button_signin_signout);
        signIn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        firebase = FirebaseFirestore.getInstance();

        simpleSearchView = findViewById(R.id.simpleSearchView);
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = convert(query);
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

    public boolean searchForDish(String dishName){
        exists = false;
        String tmp = convert(dishName);
        firebase.collection("food").whereEqualTo("name",tmp).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()&& task.getResult()!= null && !task.getResult().isEmpty()) {
                            exists = true;
                        } else {
                            exists = false;
                        }
                    }
                });
        return exists;
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
        simpleSearchView.setQuery("", false);
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

    public static String convert(String str){
        // Create a char array of given String
        char ch[] = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            // If first character of a word is found
            if (i == 0 && ch[i] != ' ' ||
                    ch[i] != ' ' && ch[i - 1] == ' ') {
                // If it is in lower-case
                if (ch[i] >= 'a' && ch[i] <= 'z') {
                    // Convert into Upper-case
                    ch[i] = (char)(ch[i] - 'a' + 'A');
                }
            }
            // If apart from first character
            // Any one is in Upper-case
            else if (ch[i] >= 'A' && ch[i] <= 'Z')
                // Convert into Lower-Case
                ch[i] = (char)(ch[i] + 'a' - 'A');
        }

        // Convert the char array to equivalent String
        String st = new String(ch);
        return st;
    }

    public void addRecipe(Map<String, String> map, final View view){
        fireBase.collection("first").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(view.getContext(), "Recipe added", Toast.LENGTH_SHORT).show();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(view.getContext(), "Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
