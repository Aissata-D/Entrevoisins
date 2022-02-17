package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */

@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
    // Tests AISSATA
    @Test
    public void createNeighbourWithSuccess() {
        String AvatarUrlToAdd = service.getNeighbours().get(0).getAvatarUrl();
        String AdressToAdd = service.getNeighbours().get(0).getAvatarUrl();
        String PhoneNuberToAdd = service.getNeighbours().get(0).getPhoneNumber();
        String AboutMeToAdd = service.getNeighbours().get(0).getAboutMe();

        Neighbour neighbourToAdd = new Neighbour(false,13,"NeighbourtoAdd"
                ,AvatarUrlToAdd,AdressToAdd,PhoneNuberToAdd,AboutMeToAdd);
        service.createNeighbour(neighbourToAdd);
        assertTrue(service.getNeighbours().contains(neighbourToAdd));
    }

    // TESTS NOUVELLE FONCTIONNALITE
    @Test
    public  void getFavoryNeighbourWithSucces(){
        Neighbour neighbour = service.getNeighbours().get(0);
        // Ajout à la liste favorite
        service.addFavoryNeighbour(neighbour);
        // verifie que la taille de liste favorite est de 1
        assertEquals(1,service.getFavoryNeigbours().size());
        // verifie que la taille de liste favorite contient neighbour
        assertTrue(service.getFavoryNeigbours().contains(neighbour));
    }
    @Test
    public void removeFavoryNeighbourWithSucces() {
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addFavoryNeighbour(neighbour);
        // verifie que la taille de liste favorite est de 1
        assertEquals(1,service.getFavoryNeigbours().size());
        service.removeFavoryNeighbour(neighbour);
        // verifie que la taille de liste favorite est de 0
        assertEquals(0,service.getFavoryNeigbours().size());
        // verifie que la liste favorite ne contient pas neighbour
        assertFalse(service.getFavoryNeigbours().contains(neighbour));
    }
    @Test
    public void addFavoryNeighbourWithSucces() {
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addFavoryNeighbour(neighbour);
        // verifie que la taille de liste favorite est de 1
        assertEquals(1,service.getFavoryNeigbours().size());
        // verifie que la liste favorite  contient neighbour
        assertTrue(service.getFavoryNeigbours().contains(neighbour));

    }
}
