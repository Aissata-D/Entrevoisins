package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.FavoriteNeighbours;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.ArrayList;
import java.util.List;


public class NeigbourgDetails extends AppCompatActivity {
    TextView mNeigbourgName;
    ImageView mNeigbourgAvatar;
    Button mNeigbourgFavoryButton;
    private NeighbourApiService mApiService ;
    List<Neighbour> mNeighbours ;
    List<FavoriteNeighbours> mFavoryListe;

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
        List<Neighbour>  mFavoryListe = new ArrayList<>();


        mNeigbourgName = findViewById(R.id.neigbourg_details_name_text);
        mNeigbourgAvatar = findViewById(R.id.neigbourg_details_avatar);
        mNeigbourgFavoryButton = findViewById(R.id.neigbourg_details_favory_button);



        mNeigbourgName.setText("PRENOM : " + nameClicked);


        Glide.with(mNeigbourgAvatar.getContext())
                .load(avatarClicked)
                .apply(RequestOptions.circleCropTransform())
                .into(mNeigbourgAvatar);

        mNeigbourgFavoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Neighbour Ab = mNeighbours.get(position);

               // mFavoryListe.clear();

                mFavoryListe.add(Ab);

               Neighbour NeighbourAddToFavory = mFavoryListe.get(0);
                //  Neighbour B = mFavoryListe.get(0);

                mNeigbourgName.setText("Prenom Add to favory: " + NeighbourAddToFavory.getName() );

              /*  Neighbour B = mFavoryListe.add(Ab.getId(),Ab.getName(),Ab.getAvatarUrl(),
                        Ab.getAddress(),
                        Ab.getPhoneNumber(),
                        Ab.getAboutMe());*/
               // mNeighbours.add(Ab);
                //mFavoryListe.clear();


            }
        });
    }


}