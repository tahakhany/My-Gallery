package com.taha.mygallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

public class AddActivity extends AppCompatActivity {

    Button saveBtn;
    Button cancelBtn;
    EditText titleEdt;
    EditText descriptionEdt;
    ImageView imageView;
    Bitmap loadedImage;
    private String TAG = "thkh1998_debugger";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Add new Image");
        setContentView(R.layout.activity_add);

        saveBtn = findViewById(R.id.add_activity_save_button);
        cancelBtn = findViewById(R.id.add_activity_cancle_button);
        titleEdt = findViewById(R.id.add_activity_title_edit_text);
        descriptionEdt = findViewById(R.id.add_activity_description_edit_text);
        imageView = findViewById(R.id.add_activity_add_image_view);

        imageView.setBackgroundColor(Color.parseColor("#363636"));

        imageView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, MainActivity.GET_IMAGE_FROM_MEMORY_REQUEST_CODE);
        });

        cancelBtn.setOnClickListener(view -> {
            finish();
        });

        saveBtn.setOnClickListener(view -> {

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);
        Log.d(TAG, "onActivityResult: resultCode: " + resultCode);
        if (requestCode == MainActivity.GET_IMAGE_FROM_MEMORY_REQUEST_CODE &&
                resultCode == RESULT_OK &&
                data != null) {
            try {
                Log.d(TAG, "onActivityResult: Build.VERSION.SDK_INT: " + Build.VERSION.SDK_INT);
                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), data.getData());
                    loadedImage = ImageDecoder.decodeBitmap(source);
                } else {
                    loadedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                }
                imageView.setImageBitmap(loadedImage);
                imageView.setBackgroundColor(getResources().getColor(R.color.transparent));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}