package com.example.ciber.and_foodproject;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AddRecipe extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button back;
    private Button save;
    private EditText text_name;
    private EditText text_description;
    private Spinner spinner;
    private ImageView mImageView;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;
    private String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        mButtonChooseImage = findViewById(R.id.button_choose_image);
        back = findViewById(R.id.button_back);
        save = findViewById(R.id.button_save_recipe);
        text_name = findViewById(R.id.text_name);
        text_description = findViewById(R.id.text_description);
        spinner = findViewById(R.id.spinner);
        mImageView = findViewById(R.id.image_view);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");


        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

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

                    uploadFile();
                    Map<String, String> map = new HashMap<>();
                    map.put("name", MainActivity.getInstance().convert(text_name.getText().toString()));
                    map.put("description", text_description.getText().toString());
                    map.put("category", spinner.getSelectedItem().toString());
                    map.put("imageURL", imageURL);
                    MainActivity.getInstance().addRecipe(map, v);
                    finish();
                }
                else{
                    Toast.makeText(AddRecipe.this, "Name already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            /*
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+ "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(AddRecipe.this, "Upload successful", Toast.LENGTH_LONG).show();
                            Upload upload = new Upload(text_name.getText().toString().trim(),
                                    taskSnapshot.getStorage().getDownloadUrl().toString());
                            mDatabaseRef.push();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddRecipe.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    */
            Uri file = Uri.fromFile(new File(mImageUri.getPath()));
            //StorageReference riversRef = mStorageRef.child(file.getLastPathSegment());
            UploadTask uploadTask =  mStorageRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(AddRecipe.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    imageURL = taskSnapshot.getStorage().getDownloadUrl().toString();
                    Toast.makeText(AddRecipe.this, "Upload successful", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
