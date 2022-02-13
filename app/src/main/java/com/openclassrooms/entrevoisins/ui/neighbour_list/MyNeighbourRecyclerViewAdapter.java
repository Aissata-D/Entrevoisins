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
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    //TODO Code Aissata
    //  private final List<Neighbour> mNeighboursFavory;
    boolean mIsFavorite;
    private int mPosition;
    //FIN

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, boolean isFavorite) {
        mIsFavorite = isFavorite;
        mNeighbours = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //Image image = new Image();
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        //  holder.mNeighbourName.setBackground();
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        Log.e("TAG, ", "onBindViewHolder: Aucun = if");
        //Masquer le bouton delete de la list des favory
        if (mIsFavorite) {
            holder.mDeleteButton.setVisibility(View.GONE);// GONE ; VISIBLE; INVISIBLE
        }
        if (!mIsFavorite) {
            //Click on a Item of Recyclerview
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPosition = holder.getAdapterPosition();
                    String NameClicked = neighbour.getName();
                    String AvatarClicked = neighbour.getAvatarUrl();
                    String AdressClicked = neighbour.getAddress();
                    String PhoneClicked = neighbour.getPhoneNumber();
                    String AboutClicked = neighbour.getAboutMe();
                    // mPosition = position;
                    Log.w("clicked", "onItemClicked: " + mPosition + " nom: " + neighbour.getName());

                    Intent neigbourgDetailsIntent = new Intent(v.getContext(), NeigbourgDetails.class);
                    neigbourgDetailsIntent.putExtra("POSITION", mPosition);
                    neigbourgDetailsIntent.putExtra("NAME_CLICKED", NameClicked);
                    neigbourgDetailsIntent.putExtra("AVATAR_CLICKED", AvatarClicked);
                    neigbourgDetailsIntent.putExtra("ADRESSE_CLICKED", AdressClicked);
                    neigbourgDetailsIntent.putExtra("PHONE_CLICKED", PhoneClicked);
                    neigbourgDetailsIntent.putExtra("ABOUT_CLICKED", AboutClicked);
                    v.getContext().startActivity(neigbourgDetailsIntent);

                }
            });
        }

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });

    }


    @Override
    public int getItemCount() {
        return mNeighbours.size();
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



