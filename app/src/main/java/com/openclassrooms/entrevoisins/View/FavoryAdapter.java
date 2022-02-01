package com.openclassrooms.entrevoisins.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.openclassrooms.entrevoisins.R;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public class FavoryAdapter extends RecyclerView.Adapter<FavoryViewHolder> {
    private List<Neighbour> mFavoryNeigbourg;
    public FavoryAdapter(List<Neighbour> FavoryNeighbur){
        this.mFavoryNeigbourg = FavoryNeighbur;
    }


    @Override
    public FavoryViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
       LayoutInflater inflater = LayoutInflater.from(context);

       // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favory_fragment_item, viewGroup,false);


       View view = inflater.inflate(R.layout.favory_fragment_item,viewGroup, false);
        return new FavoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoryViewHolder viewHolder, int i) {
        viewHolder.updateFavoryNeigbour(this.mFavoryNeigbourg.get(i));

    }

    @Override
    public int getItemCount() {
        return this.mFavoryNeigbourg.size();
    }
}
