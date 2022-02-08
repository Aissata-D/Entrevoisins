package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> implements ListNeighbourActivity.PageSelected {

    private final List<Neighbour> mNeighbours;
    //TODO Code Aissata
    //  private final List<Neighbour> mNeighboursFavory;

NeighbourFragment mFragment= new NeighbourFragment();
    FragmentManager fm;
    ListNeighbourPagerAdapter adapter = new ListNeighbourPagerAdapter(fm);
    private int mPagePosition;
    int p;
    private Neighbour ActualNeighbourg;
    private int mPosition;
    private  NeighbourFragment NeighbourFragment = new NeighbourFragment();
    private ListNeighbourActivity ListNeighbourActivity =new ListNeighbourActivity();

    //FIN

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {


        mNeighbours = items;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);


        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder,
                                 int position) {

        //if (mNeighbours !=null ) {

        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        Log.e("TAG, ","onBindViewHolder: Aucun = if");

        //Click on a Item of Recyclerview
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ActualNeighbourg= mNeighbours.get(mPosition);
                mPosition = holder.getAdapterPosition();
                String NameClicked = neighbour.getName();
                String AvatarClicked = neighbour.getAvatarUrl();
                // mPosition = position;

                Log.w("clicked", "onItemClicked: " + mPosition + " nom: " + neighbour.getName());

                Intent neigbourgDetailsIntent = new Intent(v.getContext(), NeigbourgDetails.class);
                //  new Intent().setClass(this, MyDaughterActivity.class);
                neigbourgDetailsIntent.putExtra("POSITION", mPosition);
                neigbourgDetailsIntent.putExtra("NAME_CLICKED", NameClicked);
                neigbourgDetailsIntent.putExtra("AVATAR_CLICKED", AvatarClicked);
                v.getContext().startActivity(neigbourgDetailsIntent);

            }
        });

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if( neighbour.isFavory()) {

                MyViewPager mPager = new MyViewPager();
                // mPager.getMp();
                //Log.e("TAG", "onClick: "+ mPager.getMp() );

                ListNeighbourActivity listNeighbourActivity =new ListNeighbourActivity();
                int e= listNeighbourActivity.mPageSelected;

                int pagenub= e;

                if( pagenub ==1) {
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                    Toast.makeText(v.getContext(),
                            neighbour.getName() + " on est à 1 NEIGBOURS " + pagenub,
                            Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "onClick: page "+pagenub );

                }else{
                    // neighbour.setFavory(false);
                    Toast.makeText(v.getContext(),
                            neighbour.getName() + " Veuillez supprimer dans MY NEIGBOURS  "
                                    +pagenub,
                            Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "onClick: page "+pagenub );

                }
            }
        });

    }


    @Override
    public int getItemCount() {

    return mNeighbours.size();
    }




    @Override
    public int getPageSelected(int a) {
            p = a;
        return a;
    }
    public int getP(){
        Log.e("TAGA," ,"getPagePosition: " +p );
        return p;
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



