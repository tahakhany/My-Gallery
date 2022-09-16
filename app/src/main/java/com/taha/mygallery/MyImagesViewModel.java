package com.taha.mygallery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

class MyImagesViewModel extends AndroidViewModel {

    private MyImagesRepository repository;
    private LiveData<ArrayList<MyImages>> myImages;

    public MyImagesViewModel(@NonNull Application application) {
        super(application);

        repository = new MyImagesRepository(application);
        myImages = repository.getMyImages();
    }

    public void insert(MyImages myImage) {
        repository.insert(myImage);
    }

    public void update(MyImages myImage) {
        repository.update(myImage);
    }

    public void delete(MyImages myImage) {
        repository.delete(myImage);
    }

    public LiveData<ArrayList<MyImages>> getMyImages() {
        return repository.getMyImages();
    }
}
