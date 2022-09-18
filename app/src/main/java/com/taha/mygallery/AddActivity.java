package com.taha.mygallery;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AddActivity extends AppCompatActivity {

    Button saveBtn;
    Button cancelBtn;
    EditText titleEdt;
    EditText descriptionEdt;
    ImageView imageView;

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

        imageView.setOnClickListener(view -> {

        });

        cancelBtn.setOnClickListener(view -> {
            finish();
        });

        saveBtn.setOnClickListener(view -> {

        });
    }
}