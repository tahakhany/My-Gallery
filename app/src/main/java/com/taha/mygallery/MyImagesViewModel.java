package com.taha.mygallery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyImagesViewModel extends AndroidViewModel {

    private final MyImagesRepository repository;
    private final LiveData<List<MyImages>> myImages;

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

    public LiveData<List<MyImages>> getMyImages() {
        return repository.getMyImages();
    }
}
