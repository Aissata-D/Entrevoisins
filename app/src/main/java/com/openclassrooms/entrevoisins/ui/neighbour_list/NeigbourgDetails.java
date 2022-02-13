package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.FavoriteNeighbours;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Optional;


public class NeigbourgDetails extends AppCompatActivity {
    TextView mNeigbourgName;
    TextView mNeigbourgAdresse;
    TextView mNeigbourgPhone;
    TextView mNeigbourgSocialMedia;
    TextView mNeigbourgAbout;
    private TextView mNeigbourgNameFolat;

    private FloatingActionButton mNeigbourgNameFolatBack;
    FloatingActionButton mNeigbourgFavoryButton;

    ImageView mNeigbourgAvatar;




    List<Neighbour> mNeighbours;
    List<Neighbour> mFavoryListe;
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
        mNeigbourgAdresse = findViewById(R.id.neigbourg_details_name_text_adresse);
        mNeigbourgPhone = findViewById(R.id.neigbourg_details_name_text_phone);
        mNeigbourgSocialMedia= findViewById(R.id.neigbourg_details_name_text_social_media);
        mNeigbourgAbout= findViewById(R.id.neigbourg_details_name_about_description);
        mNeigbourgNameFolat = findViewById(R.id.neigbourg_details_name_text_float);
        mNeigbourgNameFolatBack = findViewById(R.id.neigbourg_details_favory_button_back);



        mNeigbourgAvatar = findViewById(R.id.neigbourg_details_avatar);
        //mNeigbourgAvatar2 = findViewById(R.id.neigbourg_details_avatar2);

        mNeigbourgFavoryButton = findViewById(R.id.neigbourg_details_favory_button);
        Neighbour favory = mNeighbours.get(position);
     //   long idNeigbour  = mNeighbours.get(position).getId();
       // favory.setId(idNeigbour +4);
        long id = favory.getId();

        mNeigbourgName.setText(nameClicked );
        mNeigbourgNameFolat.setText( nameClicked);
        mNeigbourgAdresse.setText( adresseClicked);
        mNeigbourgPhone.setText( phoneClicked);
        mNeigbourgSocialMedia.setText( "www.facebook.fr/"+nameClicked.toLowerCase());
        mNeigbourgAbout.setText( aboutClicked);


        Glide.with(mNeigbourgAvatar.getContext())
                .load(avatarClicked)
                .centerCrop()
                .into(mNeigbourgAvatar);

        if(mNeighbours.get(position).isFavory())
        {
            mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_24_yellow);


        }else {
            mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_rate_24_gris);

        }

        mNeigbourgFavoryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if((!mFavoryListe.contains(favory))&& !favory.isFavory())
                 {
                    mApiService.addFavoryNeighbour(favory); // ajouter le voisin à Favory
                     mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_24_yellow);
                     int taille = mFavoryListe.size();
                    // mFavoryListe.get(taille-1).setId(favory.getId());
                    // mFavoryListe = mApiService.getFavoryNeigbours();
                 }else {
                   mNeigbourgFavoryButton.setImageResource(R.drawable.ic_baseline_star_rate_24_gris);

                    mApiService.removeFavoryNeighbour(favory);
                    mFavoryListe = mApiService.getFavoryNeigbours();
                }


              //  EventBus.getDefault().post(new AddNeighbourEvent(favory));
                //mApiService.addFavoryNeighbour(favory); // ajouter le voisin à Favory

               // mNeighbours.get(position).setFavory(true);// A remplacer par une methode mApiService.getNeighbourFavory
                String voisin = favory.getName();
                boolean status = favory.isFavory();
                Toast.makeText(NeigbourgDetails.this, voisin +
                        " est ajouté au favories avec staus :" +status, Toast.LENGTH_SHORT).show();

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