package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.EnemyModel;

public class RobotView implements EnemyView {
    @Override
    public void draw(TextGraphics graphics, EnemyModel model) {
        graphics.enableModifiers(SGR.BOLD);

        drawRobot(graphics, model);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.disableModifiers(SGR.BOLD);

    }

    private void drawRobot(TextGraphics graphics, EnemyModel model) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(model.getPosition().getX(), model.getPosition().getY()-3, "\\__/");
        graphics.putString(model.getPosition().getX(), model.getPosition().getY()-2, "|  |");
        graphics.putString(model.getPosition().getX(), model.getPosition().getY()-1, "|__|");
        graphics.setForegroundColor(TextColor.Factory.fromString("#DC143C"));
        graphics.putString(model.getPosition().getX()+1, model.getPosition().getY()-2, "oo");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        graphics.putString(model.getPosition().getX(), model.getPosition().getY(), " '' ");
        graphics.setForegroundColor(TextColor.Factory.fromString("#DC143C"));
        graphics.putString(model.getPosition().getX()+2, model.getPosition().getY()-5, Integer.toString(model.getHp()));
    }
}
