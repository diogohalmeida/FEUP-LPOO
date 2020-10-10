package model;


import java.util.ArrayList;

public class AlienModel extends EnemyModel {
    private ArrayList<Laser> lasers;
    private int shoot;

    public AlienModel() {
        super();
        this.hp = 2;
        this.shoot = 0;
        this.position = new Position(66,21);
        this.lasers = new ArrayList<>();
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public void setLasers(ArrayList<Laser> lasers) {
        this.lasers = lasers;
    }

    public int getShoot() {
        return shoot;
    }

    public void setShoot(int shoot) {
        this.shoot = shoot;
    }

    public void incrementShoot(){
        this.shoot++;
    }
}
