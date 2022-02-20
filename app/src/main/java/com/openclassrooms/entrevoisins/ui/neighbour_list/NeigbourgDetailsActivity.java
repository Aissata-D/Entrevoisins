package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;


public class NeigbourgDetailsActivity extends AppCompatActivity {
    TextView mNeigbourgName;
    TextView mNeigbourgAdresse;
    TextView mNeigbourgPhone;
    TextView mNeigbourgSocialMedia;
    TextView mNeigbourgAbout;
    FloatingActionButton mNeigbourgFavoryButton;
    ImageView mNeigbourgAvatar;
    List<Neighbour> mNeighbours;

    Neighbour mNeighbour;
    private TextView mNeigbourgNameFolat;
    private ImageButton mNeigbourgNameButtonBack;
    private NeighbourApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neigbourg_details);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("POSITION");
        boolean isFavorite = bundle.getBoolean("IS_FAVORITE");

        mApiService = DI.getNeighbourApiService();
        mNeighbours = isFavorite ? mApiService.getFavoryNeigbours() : mApiService.getNeighbours();
        mNeighbour = mNeighbours.get(position);

        mNeigbourgName = findViewById(R.id.neigbourg_details_name_text);
        mNeigbourgAvatar = findViewById(R.id.neigbourg_details_avatar);
        mNeigbourgAdresse = findViewById(R.id.neigbourg_details_name_text_adresse);
        mNeigbourgPhone = findViewById(R.id.neigbourg_details_name_text_phone);
        mNeigbourgSocialMedia = findViewById(R.id.neigbourg_details_name_text_social_media);
        mNeigbourgAbout = findViewById(R.id.neigbourg_details_name_about_description);
        mNeigbourgNameFolat = findViewById(R.id.neigbourg_details_name_text_float);
        mNeigbourgNameButtonBack = findViewById(R.id.neigbourg_details_favory_button_back);
        mNeigbourgFavoryButton = findViewById(R.id.neigbourg_details_favory_button);

        mNeigbourgName.setText(mNeighbour.getName());
        mNeigbourgNameFolat.setText(mNeighbour.getName());
        mNeigbourgAdresse.setText(mNeighbour.getAddress());
        mNeigbourgPhone.setText(mNeighbour.getPhoneNumber());
        mNeigbourgSocialMedia.setText("www.facebook.fr/" + mNeighbour.getName().toLowerCase());
        mNeigbourgAbout.setText(mNeighbour.getAboutMe());

        Glide.with(mNeigbourgAvatar.getContext())
                .load(mNeighbour.getAvatarUrl())
                .centerCrop()
                .into(mNeigbourgAvatar);

        if (mNeighbours.get(position).isFavory()) {
            // Adapter la couleur du bouton en ouvrant la page detail du voisin "activity_neighbour_details"

            mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_24_yellow);
        } else {
            // Adapter la couleur du bouton en ouvrant la page detail du voisin "activity_neighbour_details"

            mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_rate_24_gris);
        }

        mNeigbourgFavoryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Neighbour favory = mNeighbours.get(position);

                //if ((!mFavoryListe.contains(favory)) && !favory.isFavory())
                if (!favory.isFavory()) {
                    mApiService.addFavoryNeighbour(favory); // ajouter le voisin à Favory
                    // changer la couleur du bouton
                    mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_24_yellow);

                } else {
                    // changer la couleur du bouton
                    mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_rate_24_gris);

                    mApiService.removeFavoryNeighbour(favory);// Elever le voisin à Favory
                    // mFavoryListe = mApiService.getFavoryNeigbours();
                }
            }
        });

        mNeigbourgNameButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retour à l'element precedent
                finish();
            }
        });
    }

}