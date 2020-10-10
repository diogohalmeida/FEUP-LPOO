package model;

public class Laser {
    private Position position;
    private int direction;

    public Laser(Position position, int direction){
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public int getDirection() {
        return direction;
    }
}