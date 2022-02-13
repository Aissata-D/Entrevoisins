package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        //TOdo code AISSATA
        // juste un if else
        return NeighbourFragment.newInstance(position);
        //FIN
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }


    /**
     * get the number of pages
     *
     * @return
     */
    //TODO code Aissata Valeur de retour initial =1 ;valeur modifié = 2;
    @Override
    public int getCount() {
        return 2;
    }


}