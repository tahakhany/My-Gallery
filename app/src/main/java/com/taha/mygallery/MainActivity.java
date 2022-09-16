package com.taha.mygallery;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyImagesViewModel myImagesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImagesViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MyImagesViewModel.class);

        myImagesViewModel.getMyImages().observe(this, new Observer<ArrayList<MyImages>>() {
            @Override
            public void onChanged(ArrayList<MyImages> myImages) {
                //change recyclerView here
            }
        });
    }
}