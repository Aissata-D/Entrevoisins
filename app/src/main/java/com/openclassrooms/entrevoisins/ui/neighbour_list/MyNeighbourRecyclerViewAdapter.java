package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.FavoriteNeighbours;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    //TODO Code Aissata
    //  private final List<Neighbour> mNeighboursFavory;
    int nub;
    public List<Neighbour> mNeighboursFavory;
    List<FavoriteNeighbours> mFavoryListe = new ArrayList<>();


    //FIN

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, List<FavoriteNeighbours> items1) {

        mFavoryListe = items1;
        mNeighbours = items;
    }

  /*  public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {

        mNeighbours = items;

    }

   public MyNeighbourRecyclerViewAdapter(List<FavoriteNeighbours> items1) {

        mFavoryListe = items1;
    }
*/


   // public MyNeighbourRecyclerViewAdapter(List<FavoriteNeighbours> items1) {

     //   mFavoryListe = items1;

    //}


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mNeighbours !=null ) {
            Neighbour neighbour = mNeighbours.get(position);
            holder.mNeighbourName.setText(neighbour.getName());
            Glide.with(holder.mNeighbourAvatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.mNeighbourAvatar);
            Log.e("TAG, ","onBindViewHolder: Aucun = if");

            holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                }
            });
            //Clic item
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Neighbour neighbour = mNeighbours.get(position);
                    Intent neigbourgDetailsIntent = new Intent(getActivity(), NeigbourgDetails.class);
                    neigbourgDetailsIntent.putExtra("POSITION", position);
                    neigbourgDetailsIntent.putExtra("NAME_CLICKED", NameClicked);
                    neigbourgDetailsIntent.putExtra("AVATAR_CLICKED", AvatarClicked);
                    startActivity(neigbourgDetailsIntent);
                }
            });
        }


        else if (mFavoryListe !=null ) {
            FavoriteNeighbours neighbourFavory = mFavoryListe.get(position);
            holder.mNeighbourName.setText(neighbourFavory.getName());
            Glide.with(holder.mNeighbourAvatar.getContext())
                    .load(neighbourFavory.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.mNeighbourAvatar);

            Log.e("TAG, ","onBindViewHolder: Aucun = else if");

           /* holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbourFavory));

                }
            });*/
        }
        else {
            Log.e("TAG, ","onBindViewHolder: Aucun = else");
        }
    }

    @Override
    public int getItemCount() {
        int taille =0;

        if(mNeighbours != null){
            taille = mNeighbours.size();
            return taille;
        }
        else if (mFavoryListe !=null){
       taille = mFavoryListe.size();
            return taille;
        }
        return taille;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }


    }
};



