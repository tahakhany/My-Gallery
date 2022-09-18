package com.taha.mygallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "images_table")
class MyImages {

    @PrimaryKey(autoGenerate = true)
    private int imageId;

    private final String imageTitle;

    private final String imageDescription;

    private final byte[] image;

    @Ignore
    public MyImages(int imageId, String imageTitle, String imageDescription, byte[] image) {
        this.imageId = imageId;
        this.imageTitle = imageTitle;
        this.imageDescription = imageDescription;
        this.image = image;
    }

    public MyImages(String imageTitle, String imageDescription, byte[] image) {
        this.imageTitle = imageTitle;
        this.imageDescription = imageDescription;
        this.image = image;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public Bitmap getImageBitmap() {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
