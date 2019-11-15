package com.example.catfacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CatRowItemAdapter extends RecyclerView.Adapter<CatRowItemAdapter.CatItemViewHolder> {

    private List<Cat> catList;
    private Context mContext;


    public CatRowItemAdapter(List<Cat> catList, Context mContext) {
        this.catList = catList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public CatItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_row_item, parent, false);
        final CatItemViewHolder catItemViewHolder = new CatItemViewHolder(v);
        catItemViewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, CatDetailActivity.class);
                i.putExtra("name", catList.get(catItemViewHolder.getAdapterPosition()).getName());
                i.putExtra("id", catList.get(catItemViewHolder.getAdapterPosition()).getCatId());
                i.putExtra("cfa_url", catList.get(catItemViewHolder.getAdapterPosition()).getImage());
                i.putExtra("description", catList.get(catItemViewHolder.getAdapterPosition()).getDescription());
                i.putExtra("origin", catList.get(catItemViewHolder.getAdapterPosition()).getOrigin());
                i.putExtra("temperament", catList.get(catItemViewHolder.getAdapterPosition()).getTemperament());
                i.putExtra("wiki_url", catList.get(catItemViewHolder.getAdapterPosition()).getWikipedia_url());
                i.putExtra("dog_friendly", catList.get(catItemViewHolder.getAdapterPosition()).getDog_friendly());
                i.putExtra("life_span", catList.get(catItemViewHolder.getAdapterPosition()).getLife_span());
                i.putExtra("weight", catList.get(catItemViewHolder.getAdapterPosition()).getWeight());
                mContext.startActivity(i);
            }
        });


        return catItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatItemViewHolder holder, int position) {

        holder.catName.setText(catList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public void filterList(ArrayList<Cat> filteredList){
        catList = filteredList;
        notifyDataSetChanged();
    }


    public static class CatItemViewHolder extends RecyclerView.ViewHolder {
        public TextView catName;
        public LinearLayout view_container;
        public TextView catDescription;
        public TextView catOrigin;
        public TextView catLifeSpan;
        public TextView catTemperament;
        public TextView catWikipediaUrl;
        public TextView catDogFriendly;
        public ImageView cat_image;
        public TextView catWeight;


        public CatItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.view_container);
            catName = itemView.findViewById(R.id.cat_name);
            catDescription = itemView.findViewById(R.id.cat_description);
            catOrigin = itemView.findViewById(R.id.cat_origin);
            catLifeSpan = itemView.findViewById(R.id.cat_life_span);
            catTemperament = itemView.findViewById(R.id.cat_temperament);
            catWikipediaUrl = itemView.findViewById(R.id.cat_wiki_url);
            catDogFriendly = itemView.findViewById(R.id.cat_dog_friendly);
            catWeight = itemView.findViewById(R.id.cat_weight);
            cat_image = itemView.findViewById(R.id.cat_image);

        }
    }

}
