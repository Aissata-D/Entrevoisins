package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoryNeighbours = new ArrayList<>();


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
        long id;
        for (int i = 0; i < neighbours.size(); i++) {
            id = neighbours.get(i).getId();
            if (neighbours.get(i).isFavory()) {
                if (!(favoryNeighbours.contains(neighbours.get(i)))) {
                    favoryNeighbours.add(neighbours.get(i));
                    //favoryNeighbours.get(favoryNeighbours.size()-1).setId(id);

                }
            }else {favoryNeighbours.remove(neighbours.get(i));}
            // Enlever des favories les Neighbour supprimé de  la liste Neighbours
            for (int j = 0; j < favoryNeighbours.size(); j++) {

                if (!neighbours.contains(favoryNeighbours.get(j))) {
                    favoryNeighbours.remove(favoryNeighbours.get(j));
                }
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
