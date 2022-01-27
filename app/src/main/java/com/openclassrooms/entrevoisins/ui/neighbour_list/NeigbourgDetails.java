package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;


public class NeigbourgDetails extends AppCompatActivity {
    TextView mNeigbourgName;
    ImageView mNeigbourgAvatar;
    Button mNeigbourgFavoryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neigbourg_details);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("POSITION");
        String nameClicked = bundle.getString("NAME_CLICKED");
        String avatarClicked = bundle.getString("AVATAR_CLICKED");

        mNeigbourgName = findViewById(R.id.neigbourg_details_name_text);
        mNeigbourgAvatar = findViewById(R.id.neigbourg_details_avatar);
        mNeigbourgFavoryButton = findViewById(R.id.neigbourg_details_favory_button);

        mNeigbourgName.setText("PRENOM : " + nameClicked);


        Glide.with(mNeigbourgAvatar.getContext())
                .load(avatarClicked)
                .apply(RequestOptions.circleCropTransform())
                .into(mNeigbourgAvatar);
    }


}