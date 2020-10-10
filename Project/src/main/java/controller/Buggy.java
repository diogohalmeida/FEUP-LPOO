package controller;

import model.BuggyModel;
import model.Laser;
import model.Position;
import view.BuggyView;



public class Buggy {
    private BuggyModel model;
    private BuggyView view;


    public Buggy(){
        this.view = new BuggyView();
        this.model = new BuggyModel();
    }

    public void jump(){
        if (model.getPosition().getY() == 21) {
            this.model.getPosition().decrementY();
            this.model.setJumping(true);
            this.model.setFalling(false);
        }
    }

    public void shoot(int direction){   //0 is left, 1 is right
        if (model.getLasers().size() < 3) {
            Position position;
            if (direction == 0){
                position = new Position(35, model.getPosition().getY()-1);
            }
            else{
                position = new Position(40, model.getPosition().getY()-1);
            }
            this.model.getLasers().add(new Laser(position, direction));
       }
    }

    public void processFalling() {
        if (this.model.getFalling()){
            if (this.model.getPosition().getY() < 21) {
                this.model.getPosition().incrementY();
            }
            else {
                this.model.setFalling(false);
                this.model.setJumping(false);
            }
        }
    }

    public void processJumping() {
        if (this.model.getJumping()) {
            if (this.model.getPosition().getY() > 16) {
                this.model.getPosition().decrementY();
            }
            else {
                this.model.setFalling(true);
                this.model.setJumping(false);
            }
        }
    }

    public void processLaser(){
        if (model.getLasers().size() > 0){
            for (Laser laser: model.getLasers())
            if (laser.getDirection() == 0)
                laser.getPosition().decrementX();
            else
                laser.getPosition().incrementX();
        }
        verifyLaserLimits();
    }

    public void updateAnimPos(){
        this.model.setAnimPos(this.model.getAnimPos()+1);
        if (this.model.getAnimPos() == 4){
            this.model.setAnimPos(0);
        }
    }

    private void verifyLaserLimits(){
        model.getLasers().removeIf(laser -> laser.getPosition().getX() > 78 || laser.getPosition().getX() < 0);
    }

    public BuggyView getView() {
        return view;
    }

    public BuggyModel getModel() {
        return model;
    }
}
