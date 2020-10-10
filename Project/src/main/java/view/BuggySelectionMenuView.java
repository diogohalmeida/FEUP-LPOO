package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import controller.Game;

import java.io.IOException;

public class BuggySelectionMenuView extends MenuView {
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

        if (option == 1) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(8, 18), "It never breaks down!");
            graphics.putString(new TerminalPosition(16, 19), "Never");
        }
        graphics.putString(15, 14, "(|)-(|)");
        graphics.putString(17, 13, "0mm");
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FFFF"));
        graphics.putString(new TerminalPosition(15, 16), "Classic");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        if (option == 2) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(32, 18), "The future's LRV");
        }

        graphics.putString(36, 14, "|=\\x/=|");
        graphics.putString(38, 13, "<o>");
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        graphics.putString(new TerminalPosition(36, 16), "ApolloX");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        if (option == 3) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(54, 18), "SpaceY's CEO");
            graphics.putString(new TerminalPosition(53, 19), "favorite robot?");
        }
        graphics.putString(57, 14, "\\o/|\\o/");
        graphics.putString(59, 13, "|_|");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
        graphics.putString(new TerminalPosition(56, 16), "X AE A-12");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        if (option == 4) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        }
        graphics.putString(35, 20, "Main Menu");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

    }
}
