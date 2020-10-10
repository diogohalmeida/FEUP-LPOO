package view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import controller.Game;

import java.io.IOException;

public class PauseMenuView extends MenuView {
    @Override
    public void draw(Game game, int option) throws IOException {
        drawGUI(game, option);
        game.getScreen().refresh();
    }

    protected void drawGUI(Game game, int option) {
        TextGraphics graphics = game.getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#061639"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        for (int i = 0; i < 80; i++) {
            graphics.putString(new TerminalPosition(i, 23), "#");
        }

        graphics.enableModifiers(SGR.BOLD);
        for (int i = 31; i < 48; i++){
            graphics.putString(i, 16, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
            graphics.putString(i, 10, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
            graphics.putString(i, 6, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
        }

        for (int i = 7 ; i < 16; i++) {
            graphics.putString(30, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
            graphics.putString(48, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
        }
        graphics.putString(31, 16, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_LEFT_CORNER));
        graphics.putString(47, 16, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_RIGHT_CORNER));
        graphics.putString(31, 6, String.valueOf(Symbols.SINGLE_LINE_TOP_LEFT_CORNER));
        graphics.putString(47, 6, String.valueOf(Symbols.SINGLE_LINE_TOP_RIGHT_CORNER));


        graphics.putString(new TerminalPosition(34, 8), "GAME PAUSED");
        if (option == 1) {
            graphics.putString(new TerminalPosition(35, 14), "MAIN MENU");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(35, 12), "RESUME");
        } else {
            graphics.putString(new TerminalPosition(35, 12), "RESUME");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(35, 14), "MAIN MENU");
        }
    }

}

