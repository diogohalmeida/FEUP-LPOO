package model;

public abstract class EnemyModel {
    protected int hp;
    protected Position position;

    public int getHp() {
        return hp;
    }

    public void decreaseHp(){
        this.hp--;
    }


    public Position getPosition() {
        return position;
    }
}
