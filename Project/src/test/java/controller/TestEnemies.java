package controller;

import model.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEnemies {

    @Test
    public void testEnemiesSpawn() {
        Surface surface = new Surface(80, 24);

        Alien alien = new Alien();
        Robot robot = new Robot();

        surface.getModel().getEnemies().add(alien);
        surface.getModel().getEnemies().add(robot);

        Position alienPosition = new Position(66, 21);
        Position robotPosition = new Position(10, 21);



        assertEquals(alien.getModel().getPosition(), alienPosition);
        assertEquals(robot.getModel().getPosition(), robotPosition);

        assertEquals(alien.getModel().getHp(), 2);
        assertEquals(robot.getModel().getHp(), 3);

        assertEquals(alien.getModel().getLasers().size(), 0);
        assertEquals(robot.getModel().getAdvance(), 0);
    }

    @Test
    public void testEnemyAttack(){
        Alien alien = new Alien();
        Buggy buggy = new Buggy();
        for (int i = 0; i < 55; i++) {
            alien.doAttack();
            alien.verifyAttack(buggy.getModel());
        }
        assertTrue(buggy.getModel().getDestroyed());

        Robot robot = new Robot();

        buggy.getModel().setDestroyed(false);
        for (int i = 0; i < 88; i++){
            robot.doAttack();
            robot.verifyAttack(buggy.getModel());
        }
        assertTrue(buggy.getModel().getDestroyed());

    }

    @Test
    public void testLaserHit(){
        Buggy buggy = new Buggy();
        Robot robot = new Robot();
        Surface surface = new Surface(80, 24);
        surface.getModel().setBuggy(buggy);
        surface.getModel().getEnemies().add(robot);

        buggy.shoot(0);
        buggy.shoot(0);
        buggy.shoot(0);

        for (int i = 0; i < 24; i++){
            buggy.processLaser();
            surface.getModel().getEnemies().removeIf(enemy -> enemy.verifyHit(surface.getModel().getBuggy().getModel()) && (enemy.getModel().getHp() == 0));
        }

        assertEquals(0 ,surface.getModel().getEnemies().size());


        Alien alien = new Alien();
        surface.getModel().getEnemies().add(alien);

        buggy.shoot(1);
        buggy.shoot(1);

        for (int i = 0; i < 26; i++){
            buggy.processLaser();
            surface.getModel().getEnemies().removeIf(enemy -> enemy.verifyHit(surface.getModel().getBuggy().getModel()) && (enemy.getModel().getHp() == 0));
        }

        assertEquals(0 ,surface.getModel().getEnemies().size());
    }

    @Test
    public void testProcessEnemies(){
        Buggy buggy = new Buggy();
        Robot robot = new Robot();
        Alien alien = new Alien();
        Surface surface = new Surface(80, 24);
        surface.getModel().setBuggy(buggy);
        surface.getModel().getEnemies().add(robot);
        surface.getModel().getEnemies().add(alien);

        buggy.shoot(0);
        buggy.shoot(0);
        buggy.shoot(0);

        for (int i = 0; i < 55; i++) {      //The buggy laser should hit the robot on the left, and get hit by the alien after 55 frames
            buggy.processLaser();
            surface.processEnemies();
            if (i == 19){
                assertEquals(1 ,surface.getModel().getEnemies().size());
            }
        }
        assertTrue(buggy.getModel().getDestroyed());


    }

}
