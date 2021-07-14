package com.example.onlinestore.contents.pages.feedpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestore.MainActivity;
import com.example.onlinestore.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class FeaturedRecyclerViewAdapter extends RecyclerView.Adapter<FeaturedRecyclerViewAdapter.FeaturedViewHolder> {

    private ArrayList<ItemCardModel> featuredCardModelArrayList;
    FragmentManager fragmentManager;







    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        public MaterialCardView itemFeaturedCard;
        public TextView itemTitleTop;
        public ImageView itemPicture;
        public TextView itemDescription;
        public TextView itemRawPrice, itemFinalPrice;



        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPicture = itemView.findViewById(R.id.feed_featured_card_picture);
            itemTitleTop = itemView.findViewById(R.id.feed_featured_title_textview);
            itemDescription = itemView.findViewById(R.id.feed_card_featured_description_textview);
            itemRawPrice = itemView.findViewById(R.id.feed_card_featured_raw_price_textview);
            itemFinalPrice = itemView.findViewById(R.id.feed_card_featured_final_price_textview);
            itemFeaturedCard = itemView.findViewById(R.id.feed_featured_main_card);
        }
    }







    //adapter constructor
    public FeaturedRecyclerViewAdapter (ArrayList<ItemCardModel> featuredCardModels, FragmentManager fragmentManager) {
        this.featuredCardModelArrayList = featuredCardModels;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_featured_card_layout, parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(v);

        return featuredViewHolder;
    }






    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        ItemCardModel instanceFeaturedItemCard = featuredCardModelArrayList.get(position);

        holder.itemPicture.setImageResource(instanceFeaturedItemCard.getItemPicture());
        holder.itemTitleTop.setText(instanceFeaturedItemCard.getItemTitle());
        holder.itemDescription.setText(instanceFeaturedItemCard.getItemDescription());
        holder.itemRawPrice.setText(instanceFeaturedItemCard.getItemRawPrice());
        holder.itemFinalPrice.setText(instanceFeaturedItemCard.getItemFinalPrice());



        holder.itemFeaturedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeaturedBottomSheet featuredBottomSheet = new FeaturedBottomSheet(instanceFeaturedItemCard);
                featuredBottomSheet.show(fragmentManager , "clickedFeaturedItem");
            }
        });
    }






    @Override
    public int getItemCount() {
        return featuredCardModelArrayList.size();

    }
}
