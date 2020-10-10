package controller;

import model.BuggyModel;
import model.Laser;
import model.RobotModel;
import view.RobotView;

import java.util.Iterator;

public class Robot implements Enemy {
    private RobotModel model;
    private RobotView view;

    public Robot(){
        this.model = new RobotModel();
        this.view = new RobotView();
    }

    @Override
    public void doAttack() {
        if (model.getAdvance() == 2){
            model.getPosition().incrementX();
            model.setAdvance(0);
        }
        else{
            model.incrementAdvance();
        }
    }

    @Override
    public void verifyAttack(BuggyModel buggy) {
        if (model.getPosition().getX()+3 == buggy.getPosition().getX()){
            buggy.setDestroyed(true);
        }
    }

    @Override
    public boolean verifyHit(BuggyModel buggy) {
        if (buggy.getLasers().size() > 0) {
            for (Iterator<Laser> iterator = buggy.getLasers().iterator(); iterator.hasNext();) {
                Laser laser = iterator.next();
                if ((laser.getPosition().getX() < model.getPosition().getX() + 4) && (laser.getPosition().getY() > 17)) {
                    iterator.remove();
                    model.decreaseHp();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public RobotModel getModel() {
        return model;
    }

    public void setModel(RobotModel model) {
        this.model = model;
    }

    @Override
    public RobotView getView() {
        return view;
    }

    public void setView(RobotView view) {
        this.view = view;
    }
}
