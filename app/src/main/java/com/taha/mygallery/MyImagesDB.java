package com.taha.mygallery;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = MyImages.class, version = 1)
abstract class MyImagesDB extends RoomDatabase {

}
