package controller;

import controller.factory.*;
import model.*;
import view.SurfaceView;

import java.util.Iterator;
import java.util.Random;

public class Surface {
    private SurfaceModel model;
    private SurfaceView view;

    public Surface(int width, int height){
        model = new SurfaceModel(width, height);
        view = new SurfaceView();
    }

    public SurfaceView getView() {
        return view;
    }

    public SurfaceModel getModel() {
        return model;
    }

    public void setView(SurfaceView view) {
        this.view = view;
    }

    public void setModel(SurfaceModel model) {
        this.model = model;
    }

    public void processGame() {
        processBuggy();
        processEnemies();
        processCraterCollisions();
        processFloor();
        processTime();
    }

    public void processBuggy(){
        this.model.getBuggy().processJumping();
        this.model.getBuggy().processFalling();
        this.model.getBuggy().updateAnimPos();
        this.model.getBuggy().processLaser();
    }

    public void processTime() {
        this.model.setCurrentTime(((System.currentTimeMillis() - model.getStartTime()) - model.getPauseTime())/1000);
    }

    public void processFloor(){
        this.model.getFloor().poll();

        if (checkMinFloorSize()) return;

        if (checkMaxCraterSize()) return;

        boolean element = true;
        Random rand = new Random();
        int random = rand.nextInt(3);
        if (random == 1) {
            element = false;
            this.model.setDrawingFloor(0);
            this.model.setDrawingCrater(this.model.getDrawingCrater()+1);
        }
        else {
            this.model.setDrawingCrater(0);
            this.model.setDrawingFloor(1);
        }

        this.model.getFloor().add(element);

    }

    private boolean checkMaxCraterSize() {
        if (this.model.getDrawingCrater() == 4){
            this.model.setDrawingCrater(0);
            this.model.getFloor().add(true);
            this.model.setDrawingFloor(1);
            return true;
        }
        return false;
    }

    private boolean checkMinFloorSize() {
        if (this.model.getDrawingFloor() > 0 && this.model.getDrawingFloor() < 8){
            this.model.getFloor().add(true);
            this.model.setDrawingFloor(this.model.getDrawingFloor()+1);
            return true;
        }
        return false;
    }

    public void processCraterCollisions(){
        int x_pos = this.model.getBuggy().getModel().getPosition().getX();
        //Front wheel
        if (!((Boolean) this.model.getFloor().toArray()[79-x_pos]) && this.model.getBuggy().getModel().getPosition().getY() == 21){ //79-x
            this.model.getBuggy().getModel().setDestroyed(true);
        }

        //Rear wheel
        if (!((Boolean) this.model.getFloor().toArray()[79-(x_pos+4)]) && this.model.getBuggy().getModel().getPosition().getY() == 21){ //79-(x+4)
            this.model.getBuggy().getModel().setDestroyed(true);
        }

    }

    public void processEnemies(){
        if (this.model.getEnemies().size() < 2){                //Old SpawnEnemies()
            EnemyCreator creator = new RandomEnemyCreator();
            Enemy current = creator.createEnemy(this.model.getEnemies());
            if (current != null){
               this.model.getEnemies().add(current);
            }
        }

        for (Iterator<Enemy> iterator = this.model.getEnemies().iterator(); iterator.hasNext();){
            Enemy enemy = iterator.next();
            enemy.doAttack();

            if (enemy.verifyHit(this.model.getBuggy().getModel()) && (enemy.getModel().getHp() == 0)){
                iterator.remove();
                this.model.incrementEnemiesKilled();
            }
            enemy.verifyAttack(this.model.getBuggy().getModel());
        }
    }
}
