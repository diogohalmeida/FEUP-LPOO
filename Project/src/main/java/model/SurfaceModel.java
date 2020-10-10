package model;

import controller.Buggy;
import controller.Enemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class SurfaceModel {
    private int width, height;
    private int drawingFloor, drawingCrater;
    private int enemiesKilled;
    private double startTime, currentTime, pauseTime;
    private Buggy buggy;
    private Queue<Boolean> floor = new LinkedList<>();
    private List<Enemy> enemies = new ArrayList<>();



    public SurfaceModel(int width, int height){
        this.width = width;
        this.height = height;
        this.drawingFloor = 0;
        this.drawingCrater = 0;
        this.startTime = System.currentTimeMillis();
        this.currentTime = 0;
        this.pauseTime = 0;
        this.enemiesKilled = 0;
        for (int i = 0; i < 80; i++) floor.add(true);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Buggy getBuggy() {
        return buggy;
    }

    public void setBuggy(Buggy buggy) {
        this.buggy = buggy;
    }

    public Queue<Boolean> getFloor() {
        return floor;
    }

    public void setFloor(Queue<Boolean> floor) {
        this.floor = floor;
    }

    public int getDrawingFloor() {
        return drawingFloor;
    }

    public void setDrawingFloor(int drawingFloor) {
        this.drawingFloor = drawingFloor;
    }

    public int getDrawingCrater() {
        return drawingCrater;
    }

    public void setDrawingCrater(int drawingCrater) {
        this.drawingCrater = drawingCrater;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public double getPauseTime() {
        return pauseTime;
    }

    public void incrementPauseTime(double increment){
        this.pauseTime += increment;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void incrementEnemiesKilled(){
        this.enemiesKilled++;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

}

