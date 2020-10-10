package view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import controller.Game;

import java.io.IOException;

public class MainMenuView extends MenuView {
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

        drawTitle(graphics);

        for (int i = 0; i < 80; i++) {
            graphics.putString(new TerminalPosition(i, 23), "#");
            graphics.putString(new TerminalPosition(i, 22), "#");
        }

        graphics.enableModifiers(SGR.BOLD);
        drawBuggy(game);

        graphics.enableModifiers(SGR.BOLD);
        for (int i = 28; i <51; i++){
            graphics.putString(i, 19, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
            graphics.putString(i, 9, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
        }

        for (int i = 10 ; i < 19; i++) {
            graphics.putString(27, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
            graphics.putString(51, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
        }
        graphics.putString(50, 19, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_RIGHT_CORNER));
        graphics.putString(28, 19, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_LEFT_CORNER));
        graphics.putString(50, 9, String.valueOf(Symbols.SINGLE_LINE_TOP_RIGHT_CORNER));
        graphics.putString(28, 9, String.valueOf(Symbols.SINGLE_LINE_TOP_LEFT_CORNER));

        if (option == 1) {
            graphics.putString(new TerminalPosition(32, 15), "SCORES");
            graphics.putString(new TerminalPosition(32, 13), "BUGGY SELECTION");
            graphics.putString(new TerminalPosition(32, 17), "EXIT GAME");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(32, 11), "PLAY");
        }
        if (option == 2) {
            graphics.putString(new TerminalPosition(32, 11), "PLAY");
            graphics.putString(new TerminalPosition(32, 15), "SCORES");
            graphics.putString(new TerminalPosition(32, 17), "EXIT GAME");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(32, 13), "BUGGY SELECTION");


        }
        if (option == 3) {
            graphics.putString(new TerminalPosition(32, 11), "PLAY");
            graphics.putString(new TerminalPosition(32, 13), "BUGGY SELECTION");
            graphics.putString(new TerminalPosition(32, 17), "EXIT GAME");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(32, 15), "SCORES");

        }
        if (option == 4) {
            graphics.putString(new TerminalPosition(32, 11), "PLAY");
            graphics.putString(new TerminalPosition(32, 13), "BUGGY SELECTION");
            graphics.putString(new TerminalPosition(32, 15), "SCORES");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(32, 17), "EXIT GAME");
        }


        for (int i = 9; i <= 61; ) {
            graphics.putString(new TerminalPosition(i, 10), "IIIIIIIII");
            graphics.putString(new TerminalPosition(i, 11), "II     II");
            graphics.putString(new TerminalPosition(i, 12), "III   III");
            graphics.putString(new TerminalPosition(i, 13), " II   II ");
            graphics.putString(new TerminalPosition(i, 14), " II   II ");
            graphics.putString(new TerminalPosition(i, 15), " II   II ");
            graphics.putString(new TerminalPosition(i, 16), "III   III");
            graphics.putString(new TerminalPosition(i, 17), "II     II");
            graphics.putString(new TerminalPosition(i, 18), "IIIIIIIII");

            i += 52;
        }


    }
}
