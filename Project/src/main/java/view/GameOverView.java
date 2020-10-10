package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import controller.Game;

import java.io.IOException;

public class GameOverView {
    public void draw(Game game) throws IOException {
        drawMessage(game);
        game.getScreen().refresh();
    }

    private void drawMessage(Game game) {
        TextGraphics graphics = game.getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#061639"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(27, 17, "Press ENTER to continue");
        graphics.enableModifiers(SGR.BLINK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.putString(34, 15, "GAME OVER");
        graphics.disableModifiers(SGR.BLINK);
    }
}

