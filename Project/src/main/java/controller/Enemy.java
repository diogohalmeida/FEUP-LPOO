package controller;

import model.BuggyModel;
import model.EnemyModel;
import view.EnemyView;

public interface Enemy {
    void doAttack();

    void verifyAttack(BuggyModel buggy);

    boolean verifyHit(BuggyModel buggy);

    EnemyModel getModel();

    EnemyView getView();

}
