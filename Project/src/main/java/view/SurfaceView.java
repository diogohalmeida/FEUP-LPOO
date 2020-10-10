package view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import controller.Enemy;
import model.*;

import java.util.Random;

public class SurfaceView {
    public void draw(TextGraphics graphics, SurfaceModel model){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#061639"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getWidth(), model.getHeight()), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        drawFloor(graphics, model);

        drawSurfaceCharacters(graphics, model);

        drawScore(graphics, model);

        drawSurfaceAnimations(graphics);
    }

    private void drawScore(TextGraphics graphics, SurfaceModel model){
        graphics.enableModifiers(SGR.BOLD);
        for (int i = 0; i < 16; i++){
            graphics.putString(i, 3, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
        }

        for (int i = 0 ; i < 3; i++) {
            graphics.putString(16, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
        }
        graphics.putString(16, 3, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_RIGHT_CORNER));
        graphics.putString(1, 1, "Time:  " + model.getCurrentTime());
        graphics.putString(1, 2, "Kills: " + model.getEnemiesKilled());
        graphics.disableModifiers(SGR.BOLD);
    }


    private void drawSurfaceCharacters(TextGraphics graphics, SurfaceModel model) {
        model.getBuggy().getView().draw(graphics, model.getBuggy().getModel());

        for (Enemy enemy: model.getEnemies()){
            enemy.getView().draw(graphics, enemy.getModel());
        }
    }

    private void drawFloor(TextGraphics graphics, SurfaceModel model) {
        int count = 0;
        for (boolean x: model.getFloor()){
            if (x)
                graphics.putString(new TerminalPosition(79-count, 22), "#");
            else
                graphics.putString(new TerminalPosition(79-count, 22), " ");

            count++;
        }

        for (int i = 0; i < 80; i++){
            graphics.putString(new TerminalPosition(i, 23), "#");
        }
    }

    private void drawSurfaceAnimations(TextGraphics graphics) {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int rand_x = rand.nextInt(80);
            int rand_y = rand.nextInt(15);
            if (rand_x > 16 || rand_y > 4) {

                graphics.putString(new TerminalPosition(rand_x, rand_y), "*");
            }
        }
    }
}
