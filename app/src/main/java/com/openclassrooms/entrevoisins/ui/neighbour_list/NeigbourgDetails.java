package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;


public class NeigbourgDetails extends AppCompatActivity {
    TextView mNeigbourgName;
    TextView mNeigbourgAdresse;
    TextView mNeigbourgPhone;
    TextView mNeigbourgSocialMedia;
    TextView mNeigbourgAbout;
    FloatingActionButton mNeigbourgFavoryButton;
    ImageView mNeigbourgAvatar;
    List<Neighbour> mNeighbours;
    List<Neighbour> mFavoryListe;
    private TextView mNeigbourgNameFolat;
    private FloatingActionButton mNeigbourgNameFolatBack;
    private NeighbourApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neigbourg_details);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("POSITION");
        String nameClicked = bundle.getString("NAME_CLICKED");
        String avatarClicked = bundle.getString("AVATAR_CLICKED");
        String adresseClicked = bundle.getString("ADRESSE_CLICKED");
        String phoneClicked = bundle.getString("PHONE_CLICKED");
        String aboutClicked = bundle.getString("ABOUT_CLICKED");

        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
        mFavoryListe = mApiService.getFavoryNeigbours();

        mNeigbourgName = findViewById(R.id.neigbourg_details_name_text);
        mNeigbourgAvatar = findViewById(R.id.neigbourg_details_avatar);
        mNeigbourgAdresse = findViewById(R.id.neigbourg_details_name_text_adresse);
        mNeigbourgPhone = findViewById(R.id.neigbourg_details_name_text_phone);
        mNeigbourgSocialMedia = findViewById(R.id.neigbourg_details_name_text_social_media);
        mNeigbourgAbout = findViewById(R.id.neigbourg_details_name_about_description);
        mNeigbourgNameFolat = findViewById(R.id.neigbourg_details_name_text_float);
        mNeigbourgNameFolatBack = findViewById(R.id.neigbourg_details_favory_button_back);
        mNeigbourgFavoryButton = findViewById(R.id.neigbourg_details_favory_button);

        mNeigbourgName.setText(nameClicked);
        mNeigbourgNameFolat.setText(nameClicked);
        mNeigbourgAdresse.setText(adresseClicked);
        mNeigbourgPhone.setText(phoneClicked);
        mNeigbourgSocialMedia.setText("www.facebook.fr/" + nameClicked.toLowerCase());
        mNeigbourgAbout.setText(aboutClicked);

        Glide.with(mNeigbourgAvatar.getContext())
                .load(avatarClicked)
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

                if ((!mFavoryListe.contains(favory)) && !favory.isFavory()) {
                    mApiService.addFavoryNeighbour(favory); // ajouter le voisin à Favory
                    // changer la couleur du bouton
                    mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_24_yellow);

                } else {
                    // changer la couleur du bouton
                    mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_rate_24_gris);

                    mApiService.removeFavoryNeighbour(favory);// Elever le voisin à Favory
                    mFavoryListe = mApiService.getFavoryNeigbours();
                }
            }
        });

        mNeigbourgNameFolatBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retour à l'element precedent
                finish();
            }
        });
    }


}