package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.FavoriteNeighbours;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.Optional;


public class NeigbourgDetails extends AppCompatActivity {
    TextView mNeigbourgName;
    ImageView mNeigbourgAvatar;
    ImageView mNeigbourgFavoryButton;
    List<Neighbour> mNeighbours;
    List<FavoriteNeighbours> mFavoryListe;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neigbourg_details);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("POSITION");
        String nameClicked = bundle.getString("NAME_CLICKED");
        String avatarClicked = bundle.getString("AVATAR_CLICKED");

        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();

        mNeigbourgName = findViewById(R.id.neigbourg_details_name_text);
        mNeigbourgAvatar = findViewById(R.id.neigbourg_details_avatar);
        mNeigbourgFavoryButton = findViewById(R.id.neigbourg_details_favory_button);

        mNeigbourgName.setText("Name : " + nameClicked);

        Glide.with(mNeigbourgAvatar.getContext())
                .load(avatarClicked)
                .apply(RequestOptions.circleCropTransform())
                .into(mNeigbourgAvatar);

        mNeigbourgFavoryButton.setOnClickListener(new View.OnClickListener() {
            @Optional
            @Override
            public void onClick(View v) {
                mNeighbours.get(position).setFavory(true);
                String voisin = mNeighbours.get(position).getName();
                Toast.makeText(NeigbourgDetails.this, voisin + " est ajouté au favories", Toast.LENGTH_SHORT).show();

            }
        });
    }


}