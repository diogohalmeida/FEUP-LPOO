import java.io.IOException;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import controller.Game;
import controller.state.MenuState;
import view.MainMenuView;
import view.SoundPlayer;

public class Application {
    public static void main(String[] args) {
        try{
            //Create screen
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
            Game game = new Game(screen , new SoundPlayer());

            //Start game in main menu
            game.setState(new MenuState(game, new MainMenuView()));
            game.run();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}