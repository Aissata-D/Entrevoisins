package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.FavoriteNeighbours;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnItemClick;


public class NeighbourFragment extends Fragment {


    private NeighbourApiService mApiService;
    //private List<Neighbour> mNeighbours = new Neighbour();
    private List<Neighbour> mNeighbours;
    private List<Neighbour> mFavoryListe;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView1;
    private ListNeighbourActivity listNeighbourActivity = new ListNeighbourActivity();

    //TODO Aissata

   int mcondition;


    //FIN

    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(int condition) {
        NeighbourFragment fragment = new NeighbourFragment();
//TODO AISSATA
        Bundle bundle = new Bundle();
        bundle.putInt("CONDITION", condition);
        fragment.setArguments(bundle);

     //  mcondition = condition;
        //FIN
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getNeighbourApiService();

        //TODO Aissata

        mcondition =getArguments().getInt("CONDITION", 0);
        //FIN
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO Aissata

        mcondition = getArguments().getInt("CONDITION", 0);
        //FIN
        View view;
        view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        //View view1 = inflater.inflate(R.layout.fragment_favory, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    /**
     * Init the List of neighbours
     */
    //TODO Aissate faire un affichage conditionnel de la liste du recycler view//
    private void initList() {

// TODO differencier les instancce des listes
        mNeighbours = mApiService.getNeighbours();
        mFavoryListe =  new ArrayList<>();


        if(mcondition== 0) {

            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));

            Log.e("TAG", "initList:  case recyclerview 0 ");

        }

            else if(mcondition == 1) {
            // mFavoriteNeighbours.add(mNeighbours.get(2));

            for ( int i = 0; i < mNeighbours.size(); i++){
                if (mNeighbours.get(i).isFavory()){
                    mFavoryListe.add(mNeighbours.get(i));
                }

            }


            mRecyclerView.setAdapter(new FavoryRecyclerViewAdapter(mFavoryListe));

            Log.e("TAG", "initList:  case recyclerview 1 ");
        }
           else{

                Log.e("TAG", "initList:  No RECYCLERVIEW ");
        }
    }
public int getCondition(){
    Log.e("TAG", "getCondition: condition: " + mcondition );
        return mcondition;
}


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("TAG", "setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
        Log.e("TAG", "onResume: ONRESUME " );
        getCondition();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.e("TAG", "onStart:  ONSTART" );
        getCondition();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */

    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        if(mcondition ==0) {
            mApiService.deleteNeighbour(event.neighbour);

            initList();
        }
    }

    //TODO Aissata



}

