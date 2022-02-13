package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;
/**
 * Event fired when a user add a Neighbour
 */

public class AddNeighbourEvent {


        /**
         * Neighbour to add
         */
        public Neighbour neighbour;

        /**
         * Constructor.
         * @param neighbour
         */
        public AddNeighbourEvent(Neighbour neighbour) {
            this.neighbour = neighbour;
        }
    }


