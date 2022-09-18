package com.taha.mygallery;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

class MyImagesRepository {

   private final MyImagesDao myImagesDao;
   private final LiveData<List<MyImages>> myImages;

   public MyImagesRepository(Application application) {
      MyImagesDB myImagesDB = MyImagesDB.getInstance(application);
      myImagesDao = myImagesDB.myImagesDao();
      myImages = myImagesDao.getAllImages();
   }

   public void insert(MyImages myImage) {
      new InsertImageAsyncTask(myImagesDao).execute(myImage);
   }

   public void update(MyImages myImage) {
      new UpdateImageAsyncTask(myImagesDao).execute(myImage);
   }

   public void delete(MyImages myImage) {
      new DeleteImageAsyncTask(myImagesDao).execute(myImage);
   }

   public LiveData<List<MyImages>> getMyImages() {
      return myImages;
   }

   private class InsertImageAsyncTask extends AsyncTask<MyImages, Void, Void> {

      MyImagesDao myImagesDao;

      public InsertImageAsyncTask(MyImagesDao myImagesDao) {
         this.myImagesDao = myImagesDao;
      }

      @Override
      protected Void doInBackground(MyImages... myImages) {
         myImagesDao.insert(myImages[0]);
         return null;
      }
   }

   private class UpdateImageAsyncTask extends AsyncTask<MyImages, Void, Void> {

      MyImagesDao myImagesDao;

      public UpdateImageAsyncTask(MyImagesDao myImagesDao) {
         this.myImagesDao = myImagesDao;
      }

      @Override
      protected Void doInBackground(MyImages... myImages) {
         myImagesDao.update(myImages[0]);
         return null;
      }
   }

   private class DeleteImageAsyncTask extends AsyncTask<MyImages, Void, Void> {

      MyImagesDao myImagesDao;

      public DeleteImageAsyncTask(MyImagesDao myImagesDao) {
         this.myImagesDao = myImagesDao;
      }

      @Override
      protected Void doInBackground(MyImages... myImages) {
         myImagesDao.delete(myImages[0]);
         return null;
      }
   }
}
