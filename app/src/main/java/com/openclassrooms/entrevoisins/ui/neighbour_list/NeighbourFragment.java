package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class NeighbourFragment extends Fragment {
    int mCondition;
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private List<Neighbour> mFavoryListe;
    private RecyclerView mRecyclerView;

    private ListNeighbourActivity listNeighbourActivity = new ListNeighbourActivity();

    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(int condition) {
        NeighbourFragment fragment = new NeighbourFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("CONDITION", condition);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        mCondition = getArguments().getInt("CONDITION", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mCondition = getArguments().getInt("CONDITION", 0);
        View view;
        view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        boolean isFavorite = (mCondition == 1);
        mNeighbours = mApiService.getNeighbours();
        mFavoryListe = mApiService.getFavoryNeigbours();
        if (!isFavorite) {
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, isFavorite));
        } else {
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavoryListe, isFavorite));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
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
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

}

