package com.taha.mygallery;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;

@Dao
interface MyImagesDao {

    @Insert
    void insert(MyImages image);

    @Delete
    void delete(MyImages image);

    @Update
    void update(MyImages image);

    @Query("SELECT * FROM images_table ORDER BY imageId ASC")
    LiveData<ArrayList<MyImages>> getAllImages();

}
