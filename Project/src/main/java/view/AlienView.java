package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.AlienModel;
import model.EnemyModel;
import model.Laser;

public class AlienView implements EnemyView {
    @Override
    public void draw(TextGraphics graphics, EnemyModel model) {
        graphics.enableModifiers(SGR.BOLD);

        drawAlien(graphics, model);

        drawAlienLasers(graphics, (AlienModel) model);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.disableModifiers(SGR.BOLD);
    }

    private void drawAlienLasers(TextGraphics graphics, AlienModel model) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        if (model.getLasers().size() > 0){
            for (Laser laser: model.getLasers())
                graphics.putString(new TerminalPosition(laser.getPosition().getX(), laser.getPosition().getY()), "-");
        }
    }

    private void drawAlien(TextGraphics graphics, EnemyModel model) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(model.getPosition().getX(), model.getPosition().getY() - 2, "_ \\_");
        graphics.setForegroundColor(TextColor.Factory.fromString("#3CD070"));
        graphics.putString(model.getPosition().getX()+1, model.getPosition().getY() - 2, "O");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(model.getPosition().getX(), model.getPosition().getY()-1, "\\__/");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        graphics.putString(model.getPosition().getX(), model.getPosition().getY(), " '' ");
        graphics.setForegroundColor(TextColor.Factory.fromString("#DC143C"));
        graphics.putString(model.getPosition().getX()+1, model.getPosition().getY()-5, Integer.toString(model.getHp()));
    }
}
