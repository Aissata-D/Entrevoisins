package com.openclassrooms.entrevoisins.service;

import android.support.v4.app.FragmentManager;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourPagerAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

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
import static org.mockito.Mockito.*;


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
 /*   @Test

    //public void onPageSelected(int i)
    public void onPageSelectedFavory() {
        int i= 0;
        int k= 0;
        List<Neighbour> neighbours = service.getNeighbours();
      //  NeighbourFragment fragment = mock(NeighbourFragment.newInstance(i));

        NeighbourFragment fragment = mock(NeighbourFragment.class);
        // NeighbourFragment fragment1 = mock(NeighbourFragment.newInstance(i).class);
     //   FragmentManager fm ;
       // ListNeighbourPagerAdapter list ;
        fragment.getChildFragmentManager();
       boolean A = when(NeighbourFragment.newInstance(i)).;
      //  if (list.getItem(1)){
            for(int j = 0;j < neighbours.size(); j ++){
               when(NeighbourFragment.newInstance(k));

                assertEquals(false,neighbours.get(j).isFavory());
                assertEquals(false, when(NeighbourFragment.newInstance(1))
                        .thenReturn(fragment.onCreateView().);
            }

       // }

    }*/


}
