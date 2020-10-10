package controller;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TestSurface {
    @Test
    public void testProcessCollisions() {
        //Initial State, no Collision
        Surface surface = new Surface(80,24);
        surface.getModel().setBuggy(new Buggy());

        surface.processCraterCollisions();
        assertFalse(surface.getModel().getBuggy().getModel().getDestroyed());


        //Front-Wheel Collision
        Queue<Boolean> floor = new LinkedList<>();
        for (int i = 0; i < 80; i++){
            if (i == 44)
                floor.add(false);
            else
                floor.add(true);
        }
        surface.getModel().setFloor(floor);

        surface.processCraterCollisions();
        assertTrue(surface.getModel().getBuggy().getModel().getDestroyed());


        //Rear-Wheel Collision
        surface.getModel().getBuggy().getModel().setDestroyed(false);
        floor.clear();
        for (int i = 0; i < 80; i++){
            if (i == 40)
                floor.add(false);
            else
                floor.add(true);
        }
        surface.getModel().setFloor(floor);

        surface.processCraterCollisions();
        assertTrue(surface.getModel().getBuggy().getModel().getDestroyed());


        //Jumping Over a Hole
        surface.getModel().getBuggy().getModel().setDestroyed(false);
        surface.getModel().getBuggy().jump();
        surface.getModel().getBuggy().processJumping();

        surface.processCraterCollisions();
        assertFalse(surface.getModel().getBuggy().getModel().getDestroyed());
    }


    @Test
    public void testProcessFloor() {
        Surface surface = new Surface(80, 24);
        boolean passed = true;

        for (int i = 0; i < 10000; i++){    //Testing the floor for 10000 frames of the game
            surface.processFloor();
            List current_floor = (List) surface.getModel().getFloor();
            Boolean current = true;
            int count = 0;
            for (int j = 0; j < current_floor.size(); j++){
                if (j == 0){
                    current = (Boolean) current_floor.get(j);
                    count = 1;
                    continue;
                }

                if (!current && count > 4){             //Verifies if there is a crater with more than 4 spaces
                    passed = false;
                }

                if (current_floor.get(j) == current){
                    count++;
                }
                else {
                    if (current && count < 8 && j > 7){ //Verifies if there is a surface with less than 8 spaces
                        passed = false;
                    }
                    count = 1;
                    current = (Boolean) current_floor.get(j);
                }
            }
        }

        assertTrue(passed);
    }

    @Test
    public void testProcessGame() {
        Surface surface = Mockito.mock(Surface.class);

        doCallRealMethod().when(surface).processGame();
        surface.processGame();
        verify(surface, times(1)).processGame();

        verify(surface, times(1)).processBuggy();
        verify(surface, times(1)).processEnemies();
        verify(surface, times(1)).processFloor();
        verify(surface, times(1)).processCraterCollisions();
        verify(surface, times(1)).processTime();
    }

    @Test
    public void testProcessBuggy() {
        Surface surface = new Surface(80, 24);
        Buggy buggyMock = Mockito.mock(Buggy.class);
        surface.getModel().setBuggy(buggyMock);
        surface.processBuggy();


        verify(buggyMock, times(1)).processJumping();
        verify(buggyMock, times(1)).processFalling();
        verify(buggyMock, times(1)).updateAnimPos();
        verify(buggyMock, times(1)).processLaser();
    }
}
