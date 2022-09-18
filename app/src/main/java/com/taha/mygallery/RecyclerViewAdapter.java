package com.taha.mygallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardView> {

   Context context;
   List<MyImages> myImages;

   public RecyclerViewAdapter(Context context) {
      this.context = context;
   }

   public void setMyImages(List<MyImages> myImages) {
      this.myImages = myImages;
      notifyDataSetChanged();
   }

   @NonNull
   @Override
   public CardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = View.inflate(context, R.layout.card_view_layout, parent);
      /*View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,
              parent,false);*/
      return new CardView(view);
   }

   @Override
   public void onBindViewHolder(@NonNull CardView holder, int position) {

   }

   @Override
   public int getItemCount() {
      try {
         return myImages.size();
      } catch (Exception e) {
         return 0;
      }

   }

   class CardView extends RecyclerView.ViewHolder {

      ImageView image;
      TextView title;
      TextView description;
      ConstraintLayout card;

      public CardView(@NonNull View itemView) {
         super(itemView);
         image = itemView.findViewById(R.id.card_view_image_view);
         title = itemView.findViewById(R.id.card_view_image_title);
         description = itemView.findViewById(R.id.card_view_image_description);
         card = itemView.findViewById(R.id.card_view_card_view);
      }
   }
}
