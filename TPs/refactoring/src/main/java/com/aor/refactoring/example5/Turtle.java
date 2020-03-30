package com.aor.refactoring.example5;

public class Turtle {
    private int row;
    private int column;
    private char direction;

    public Turtle(int row, int column, char direction) {
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getDirection() {
        return direction;
    }

    public void execute(char command) {
        switch (command){
            case 'L':
                rotateLeft();
                break;
            case 'R':
                rotateRight();
                break;
            case 'F':
                moveForward();
                break;
        }
    }

    private void rotateLeft() {
        switch (direction) {
            case 'N':
                direction = 'W';
                break;
            case 'W':
                direction = 'S';
                break;
            case 'S':
                direction = 'E';
                break;
            case 'E':
                direction = 'N';
                break;
        }
    }

    private void rotateRight() {
        switch (direction) {
            case 'N':
                direction = 'E';
                break;
            case 'E':
                direction = 'S';
                break;
            case 'S':
                direction = 'W';
                break;
            case 'W':
                direction = 'N';
                break;
        }
    }

    private void moveForward() {
        switch (direction) {
            case 'N':
                moveUp();
                break;
            case 'E':
                moveRight();
                break;
            case 'S':
                moveDown();
                break;
            case 'W':
                moveLeft();
                break;
        }
    }

    private int moveLeft() {
        return column--;
    }

    private int moveDown() {
        return row++;
    }

    private int moveRight() {
        return column++;
    }

    private void moveUp() {
        row--;
    }


}
