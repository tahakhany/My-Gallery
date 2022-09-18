package com.taha.mygallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
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
    Bitmap scaledImage;

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

        //imageView.setBackgroundColor(Color.parseColor("#363636"));

        imageView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, MainActivity.GET_IMAGE_FROM_MEMORY_REQUEST_CODE);
        });

        cancelBtn.setOnClickListener(view -> {
            finish();
        });

        saveBtn.setOnClickListener(view -> {
            if (loadedImage != null) {
                String title = titleEdt.getText().toString();
                String description = descriptionEdt.getText().toString();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                scaledImage = MainActivity.makeSmall(loadedImage, 300);
                scaledImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                byte[] image = outputStream.toByteArray();

                Intent intent = new Intent(AddActivity.this, MainActivity.class);

                intent.putExtra(MainActivity.IMAGE_KEY, image);
                intent.putExtra(MainActivity.TITLE_KEY, title);
                intent.putExtra(MainActivity.DESCRIPTION_KEY, description);

                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "Please Select an Image first", Toast.LENGTH_LONG)
                        .show();
            }

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
                //imageView.setBackgroundColor(getResources().getColor(R.color.transparent));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}