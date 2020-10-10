package model;

import java.util.ArrayList;

public class BuggyModel {
    private int animPos, type;
    private boolean isJumping, isFalling, isDestroyed;
    private Position position;
    private ArrayList<Laser> lasers;


    public BuggyModel(){
        position = new Position(35,21);
        animPos = 0;
        isFalling = false;
        isJumping = false;
        isDestroyed = false;
        lasers = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
    }

    public int getAnimPos() {
        return animPos;
    }

    public void setAnimPos(int animPos) {
        this.animPos = animPos;
    }

    public boolean getJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        this.isJumping = jumping;
    }

    public boolean getFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        this.isFalling = falling;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public boolean getDestroyed() {
        return this.isDestroyed;
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public void setLasers(ArrayList<Laser> lasers) {
        this.lasers = lasers;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
