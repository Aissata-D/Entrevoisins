package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

   public int mPagePosition;
    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        //TOdo code AISSATA
        // juste un if else
       // if (position==0) {
            //this.mposition = 0;
            Log.e("TAG", "getItem: POSITION PAGE : " + position);
            return NeighbourFragment.newInstance(position);


        //FIN
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }




    /**
     * get the number of pages
     * @return
     */
    //TODO code Aissata Valeur de retour initial =1 ;valeur modifié = 2;
    @Override
    public int getCount(){
        return 2;
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {
        Log.e("TAG", "onPageScrolled: " );

    }

    @Override
    public void onPageSelected(int i) {
        mPagePosition = i;
        Log.e("TAG", "onPageSelected: "+i );

    }
    public int getPagePosition(){
        Log.e("TAG," ,"getPagePosition: " +mPagePosition );
        return mPagePosition;
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        Log.e("TAG", "onPageScrollStateChanged: " );

    }
}