package controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestBuggy {
    @Test
    public void testJumpBuggy() {
        Buggy buggy = new Buggy();

        buggy.jump();

        assertTrue(buggy.getModel().getJumping());
        assertFalse(buggy.getModel().getFalling());
        assertEquals(20, buggy.getModel().getPosition().getY());    //Making sure the buggy has jumped
    }

    @Test
    public void testJumpingBuggy() {
        Buggy buggy = new Buggy();

        buggy.getModel().setJumping(true);
        buggy.getModel().setFalling(false);
        buggy.getModel().getPosition().setY(20);

        buggy.processJumping();
        assertEquals(19,buggy.getModel().getPosition().getY());    //First processJumping call -1 yPosition

        buggy.processJumping();
        assertEquals(18, buggy.getModel().getPosition().getY());   //Second processJumping call -1 yPosition

        buggy.processJumping();
        assertEquals(17, buggy.getModel().getPosition().getY());   //Third processJumping call -1 yPosition

        buggy.processJumping();
        assertEquals(16, buggy.getModel().getPosition().getY());   //Fourth processJumping call -1 yPosition

        buggy.processJumping();
        assertEquals(16,buggy.getModel().getPosition().getY());    //Fifth processJumping call, same yPosition, change getFalling and getJumping to true and false respectively
        assertTrue(buggy.getModel().getFalling());
        assertFalse(buggy.getModel().getJumping());
    }

    @Test
    public void testFallingBuggy(){
        Buggy buggy = new Buggy();

        buggy.getModel().setJumping(false);
        buggy.getModel().setFalling(true);
        buggy.getModel().getPosition().setY(16);

        buggy.processFalling();
        assertEquals(17,buggy.getModel().getPosition().getY());    //First processFalling call +1 yPosition

        buggy.processFalling();
        assertEquals(18, buggy.getModel().getPosition().getY());   //Second processFalling call +1 yPosition

        buggy.processFalling();
        assertEquals(19, buggy.getModel().getPosition().getY());    //Third processFalling call +1 yPosition

        buggy.processFalling();
        assertEquals(20, buggy.getModel().getPosition().getY());    //Fourth processFalling call +1 yPosition

        buggy.processFalling();
        assertEquals(21,buggy.getModel().getPosition().getY());    //Fifth processFalling call +1 yPosition

        buggy.processFalling();
        assertEquals(21,buggy.getModel().getPosition().getY());    //Sixth processFalling call, same yPosition, change both getFalling and getJumping to false
        assertFalse(buggy.getModel().getFalling());
        assertFalse(buggy.getModel().getJumping());
    }

    @Test
    public void testBuggyAnimations(){
        Buggy buggy = new Buggy();

        assertEquals(0,buggy.getModel().getAnimPos());

        buggy.updateAnimPos();
        assertEquals(1,buggy.getModel().getAnimPos());

        buggy.updateAnimPos();
        assertEquals(2,buggy.getModel().getAnimPos());

        buggy.updateAnimPos();
        assertEquals(3,buggy.getModel().getAnimPos());

        buggy.updateAnimPos();
        assertEquals(0,buggy.getModel().getAnimPos());

    }
}
