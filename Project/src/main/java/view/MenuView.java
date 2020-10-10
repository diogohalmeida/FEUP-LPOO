package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import controller.Game;

import java.io.IOException;

public abstract class MenuView {
    public abstract void draw(Game game, int option) throws IOException;

    protected abstract void drawGUI(Game game, int option);

    protected void drawTitle(TextGraphics graphics) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(2, 1), "MM     MM  OOOOO   OOOOO  NN     N   BBBBBB  U     U  GGGGG   GGGGG  Y     Y");
        graphics.putString(new TerminalPosition(2, 2), "M M   M M O     O O     O N N    N   B     B U     U G     G G     G  Y   Y");
        graphics.putString(new TerminalPosition(2, 3), "M  M M  M O     O O     O N  N   N   BBBBBB  U     U G       G         Y Y");
        graphics.putString(new TerminalPosition(2, 4), "M   M   M O     O O     O N   N  N   B     B U     U G   GGG G   GGG    Y ");
        graphics.putString(new TerminalPosition(2, 5), "M       M O     O O     O N    N N   B     B U     U G     G G     G    Y");
        graphics.putString(new TerminalPosition(2, 6), "M       M  OOOOO   OOOOO  N     NN   BBBBBB   UUUUU   GGGGG   GGGGG     Y");

        graphics.enableModifiers(SGR.BLINK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.putString(new TerminalPosition(30, 8), "When Gravity Fails!");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.disableModifiers(SGR.BOLD);
        graphics.disableModifiers(SGR.BLINK);


    }

    protected void drawBuggy(Game game) {
        int type = game.getType();
        TextGraphics graphics = game.getScreen().newTextGraphics();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#061639"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        if (type == 1) {
            graphics.putString(36, 21, "(|)-(|)");
            graphics.putString(38, 20, "0mm");
        }

        if (type == 2){
            graphics.putString(36, 21, "|=\\x/=|");
            graphics.putString(38, 20, "<o>");
        }
        if (type == 3){
            graphics.putString(36, 21, "\\o/|\\o/");
            graphics.putString(38, 20, "|_|");
        }

        graphics.disableModifiers(SGR.BOLD);
    }


}
