package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class FavoryRecyclerViewAdapter extends
        RecyclerView.Adapter<FavoryRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mFavoryNeighbours;

    public FavoryRecyclerViewAdapter(List<Neighbour> neighbours) {
        mFavoryNeighbours = neighbours;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favory_fragment_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Neighbour favoryneighbour = mFavoryNeighbours.get(i);
        viewHolder.fNeighbourName.setText(favoryneighbour.getName());

        Glide.with(viewHolder.fNeighbourAvatar.getContext())
                .load(favoryneighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.fNeighbourAvatar);
    }

    @Override
    public int getItemCount() {
        return mFavoryNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_favory_avatare)
        public ImageView fNeighbourAvatar;

        @BindView(R.id.item_favory_name)
        public TextView fNeighbourName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
};
