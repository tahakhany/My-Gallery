package com.taha.mygallery;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = MyImages.class, version = 1)
abstract class MyImagesDB extends RoomDatabase {

    private static final String DATABASE_NAME = "my_images_database";
    private static MyImagesDB database;

    public static synchronized MyImagesDB getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            MyImagesDB.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract MyImagesDao myImagesDao();
}
