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
import com.openclassrooms.entrevoisins.utils.ItemClickSupport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnItemClick;


public class NeighbourFragment extends Fragment {


    private NeighbourApiService mApiService;
    //private List<Neighbour> mNeighbours = new Neighbour();
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private ListNeighbourActivity listNeighbourActivity = new ListNeighbourActivity();

    //TODO Aissata
    Neighbour ActualNeighbourg;
    private int mPosition;
   static int mcondition =0;
   // private List<Neighbour> mFavoriteNeighbours;
    //List<Neighbour>  mFavoriteNeighbours = new ArrayList<>();
    List<FavoriteNeighbours> mFavoryListe = new ArrayList<>();

    //FIN

    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(boolean condition) {
        NeighbourFragment fragment = new NeighbourFragment();
//TODO AISSATA
        Bundle bundle = new Bundle();
        bundle.putBoolean("CONDITION", condition);
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

        mcondition =getArguments().getInt("CONDITION", 3);
        //FIN
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO Aissata

        mcondition =getArguments().getInt("CONDITION", 3);
        //FIN
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        configureOnClickRecyclerView();

        return view;
    }

    /**
     * Init the List of neighbours
     */
    //TODO Aissate faire un affichage conditionnel de la liste du recycler view//
    private void initList() {
       // mNeighbours = mApiService.getNeighbours();
       // mFavoriteNeighbours.add(mNeighbours.get(2));
       // mFavoriteNeighbours = new ArrayList<>();
       // mFavoriteNeighbours = mApiService.getNeighbours();

        //mFavoriteNeighbours.clear();
// TODO differencier les instancce des listes
        mNeighbours = mApiService.getNeighbours();

        if(mcondition== 0) {

            //   List< Neighbour> NeighbourTest = new ArrayList<>();
            // NeighbourTest.add(mNeighbours.get(6));
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, null));
            String A = mNeighbours.get(1).getName();

            Log.e("TAG", "initList:  case recyclerview 0 ");
        }

            else if(mcondition == 1) {
            // mFavoriteNeighbours.add(mNeighbours.get(2));
            FavoriteNeighbours test = new FavoriteNeighbours(1, "Aissata", mNeighbours.get(2).getAvatarUrl(), "adress 2",
                    "07 09", "ABOUTme");
            mFavoryListe.add(test);


            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(null, mFavoryListe));
            // mNeighbours = mApiService.getNeighbours();
            //mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours,null));
            Log.e("TAG", "initList:  case recyclerview 1 ");
        }
           else{
              //  mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours,null));
              //  String A = mNeighbours.get(1).getName();
                Log.e("TAG", "initList:  No RECYCLERVIEW ");
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

    //TODO Aissata

    private void configureOnClickRecyclerView() {

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // do it
                ActualNeighbourg = mNeighbours.get(position);
                String NameClicked = ActualNeighbourg.getName();
                String AvatarClicked = ActualNeighbourg.getAvatarUrl();
                mPosition = position;

                Log.w("clicked", "onItemClicked: " + position + " nom: " + ActualNeighbourg.getName());

                Intent neigbourgDetailsIntent = new Intent(getActivity(), NeigbourgDetails.class);
                neigbourgDetailsIntent.putExtra("POSITION", position);
                neigbourgDetailsIntent.putExtra("NAME_CLICKED", NameClicked);
                neigbourgDetailsIntent.putExtra("AVATAR_CLICKED", AvatarClicked);
                startActivity(neigbourgDetailsIntent);
            }
        });


    }

}

