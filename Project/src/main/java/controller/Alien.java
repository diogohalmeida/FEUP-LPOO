package controller;

import model.AlienModel;
import model.BuggyModel;
import model.Laser;
import model.Position;
import view.AlienView;

import java.util.Iterator;

public class Alien implements Enemy {
    private AlienModel model;
    private AlienView view;

    public Alien(){
        this.model = new AlienModel();
        this.view = new AlienView();
    }

    @Override
    public void doAttack() {
        if (model.getShoot() == 30){
            Position position = new Position(model.getPosition().getX()-1, model.getPosition().getY()-1);
            model.getLasers().add(new Laser(position,0));
            model.setShoot(0);
        }
        else{
            model.incrementShoot();
        }

        if (model.getLasers().size() > 0){
            for (Laser laser: model.getLasers())
                laser.getPosition().decrementX();
        }
        verifyLaserLimits();
    }

    private void verifyLaserLimits(){
        model.getLasers().removeIf(laser -> laser.getPosition().getX() > 78 || laser.getPosition().getX() < 0);
    }

    @Override
    public void verifyAttack(BuggyModel buggy) {
        for (Laser laser: model.getLasers())
        if ((laser.getPosition().getX() == buggy.getPosition().getX()+5) && (laser.getPosition().getY() == buggy.getPosition().getY()-1)){
            buggy.setDestroyed(true);
        }
    }

    @Override
    public boolean verifyHit(BuggyModel buggy) {
        if (buggy.getLasers().size() > 0) {
            for (Iterator<Laser> iterator = buggy.getLasers().iterator(); iterator.hasNext();) {
                Laser laser = iterator.next();
                if ((laser.getPosition().getX() > model.getPosition().getX() - 2) && (laser.getPosition().getY() > 18)) {
                    iterator.remove();
                    model.decreaseHp();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public AlienModel getModel() {
        return model;
    }

    public void setModel(AlienModel model) {
        this.model = model;
    }

    @Override
    public AlienView getView() {
        return view;
    }

    public void setView(AlienView view) {
        this.view = view;
    }
}

