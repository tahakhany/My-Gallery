package com.taha.mygallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    MyImagesViewModel myImagesViewModel;
    public static final int ADD_ACTIVITY_CODE = 1;
    public static final int READ_EXTERNAL_MEMORY_REQUEST_CODE = 1000;
    public static final int GET_IMAGE_FROM_MEMORY_REQUEST_CODE = 100;
    public static final String IMAGE_KEY = "image";
    public static final String TITLE_KEY = "title";
    public static final String DESCRIPTION_KEY = "description";
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    FloatingActionButton FAB;
    List<MyImages> myImages;

    public static Bitmap makeSmall(Bitmap image, int maxSize) {
        float width = image.getWidth();
        float height = image.getHeight();
        float ratio = width / height;

        if (ratio >= 1) {
            width = maxSize;
            height = width / ratio;
        } else {
            height = maxSize;
            width = ratio * height;
        }
        return Bitmap.createScaledBitmap(image, (int) width, (int) height, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My Gallery");
        setContentView(R.layout.activity_main);

        FAB = findViewById(R.id.main_activity_floating_action_button);

        recyclerView = findViewById(R.id.main_activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        myImagesViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MyImagesViewModel.class);

        myImagesViewModel.getMyImages().observe(this, myImages -> {
            this.myImages = myImages;
            recyclerViewAdapter.setMyImages(myImages);
        });

        FAB.setOnClickListener(view -> {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, ADD_ACTIVITY_CODE);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        READ_EXTERNAL_MEMORY_REQUEST_CODE);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                MyImages image = myImages.get(position);
                myImagesViewModel.delete(image);
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ADD_ACTIVITY_CODE && data != null) {

            String title = data.getStringExtra(TITLE_KEY);
            String description = data.getStringExtra(DESCRIPTION_KEY);
            byte[] image = data.getByteArrayExtra(IMAGE_KEY);
            MyImages myImage = new MyImages(title, description, image);
            myImagesViewModel.insert(myImage);
        }
    }
}