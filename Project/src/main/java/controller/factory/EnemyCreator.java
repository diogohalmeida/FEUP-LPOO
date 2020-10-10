package controller.factory;

import controller.Enemy;

import java.util.List;

public interface EnemyCreator {
    Enemy createEnemy(List<Enemy> enemies);
}
