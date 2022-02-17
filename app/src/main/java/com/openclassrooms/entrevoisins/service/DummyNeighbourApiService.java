package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();



    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    // Methode FavoryNeighbour

    @Override
    public List<Neighbour> getFavoryNeigbours() {
        List<Neighbour> favoryNeighbours = new ArrayList<>();
        for (Neighbour n: neighbours) {
            if (n.isFavory()) {
                favoryNeighbours.add(n);
            }
        }
        return favoryNeighbours;
    }

    @Override
    public void removeFavoryNeighbour(Neighbour favoryNeighbour) {
        favoryNeighbour.setFavory(false);
        //  favoryNeighbours.remove(favoryNeighbour);
    }

    @Override
    public void addFavoryNeighbour(Neighbour favoryNeighbour) {
        favoryNeighbour.setFavory(true);
        // favoryNeighbours.add(favoryNeighbour);
    }
    // Implementer les trois methodes ajouté
    // Boucle
    //  void createNeighbourFavorite(Neighbour neighbour){neighbours.get(neighbour)}
   /* @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        for(Neighbour n:neighbours){
            if(n.equals(neighbour)){
                n.setIsFavorite(true);
            }
        }
    }*/
}
