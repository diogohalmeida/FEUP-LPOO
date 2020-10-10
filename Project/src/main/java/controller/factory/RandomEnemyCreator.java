package controller.factory;
import controller.Alien;
import controller.Enemy;
import controller.Robot;

import java.util.List;
import java.util.Random;

public class RandomEnemyCreator implements EnemyCreator {
    @Override
    public Enemy createEnemy(List<Enemy> enemies) {
        if (enemies.size() == 0){
            Random rand = new Random();
            int spawn = rand.nextInt(80);   //Decide if the first enemy is created
            if (spawn == 0){
                int random = rand.nextInt(2);   //Decide which type of enemy is created
                if (random == 0){
                    return new Robot();
                } else {
                    return new Alien();
                }
            }
        }
        else if (enemies.size() == 1) {
            Random rand = new Random();
            int spawn = rand.nextInt(160);  //Decide if the second enemy is created
            if (spawn == 0) {
                if (enemies.get(0) instanceof Robot) {  //Create another type of enemy different to the first one
                    return new Alien();
                } else {
                    return new Robot();
                }
            }
        }
        return null;
    }
}
