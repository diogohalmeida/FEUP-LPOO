package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import controller.Game;
import model.Score;

import java.io.IOException;

public class ScoresMenuView extends MenuView {
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

        graphics.putString(new TerminalPosition(24, 10), "Rank  Time       Kills  Date");
        int count = 1;
        for (Score score: game.getScores()){
            if (count == 6-option) {
                graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            }
            String output = String.format("%-2d    %-7.3f    %-3d    %-10s", count, score.getTime(), score.getEnemiesKilled(), score.getDate());
            graphics.putString(new TerminalPosition(24, 11+count), output);
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            count++;
        }
        if (option == 0) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        }
        graphics.putString(new TerminalPosition(35, 18), "Main Menu");

    }
}
