package com.openclassrooms.entrevoisins.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;

public class FavoryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_favory_fragment_textView)
    TextView mTextView_name;
    public FavoryViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public void updateFavoryNeigbour(Neighbour FavoryNeigbour){
        this.mTextView_name.setText(FavoryNeigbour.getName());
    }

}
