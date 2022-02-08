package com.openclassrooms.entrevoisins.model;

public class FavoriteNeighbours extends Neighbour{

    /**
     * Constructor
     *
     * @param isFavory
     * @param id
     * @param name
     * @param avatarUrl
     * @param address
     * @param phoneNumber
     * @param aboutMe
     */
    public FavoriteNeighbours(boolean isFavory, long id, String name, String avatarUrl, String address, String phoneNumber, String aboutMe) {
        super(isFavory, id, name, avatarUrl, address, phoneNumber, aboutMe);
    }
}
