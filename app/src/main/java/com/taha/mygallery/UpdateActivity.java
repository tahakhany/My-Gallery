package com.taha.mygallery;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    Button updateBtn;
    Button cancelBtn;
    EditText titleEdt;
    EditText descriptionEdt;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateBtn = findViewById(R.id.update_activity_update_button);
        cancelBtn = findViewById(R.id.update_activity_cancel_button);
        titleEdt = findViewById(R.id.update_activity_title_edit_text);
        descriptionEdt = findViewById(R.id.update_activity_description_edit_text);
        imageView = findViewById(R.id.update_activity_update_image_view);

        updateBtn.setOnClickListener(view -> {

        });

        cancelBtn.setOnClickListener(view -> {
            finish();
        });

        imageView.setOnClickListener(view -> {

        });
    }
}