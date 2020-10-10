package model;

public class RobotModel extends EnemyModel {
    private int advance;

    public RobotModel() {
        super();
        this.hp = 3;
        this.position = new Position(10,21);
    }

    public int getAdvance() {
        return advance;
    }

    public void setAdvance(int advance) {
        this.advance = advance;
    }

    public void incrementAdvance(){
        this.advance++;
    }
}
