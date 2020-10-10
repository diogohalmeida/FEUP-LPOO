package controller.state;

import controller.Game;
import view.KeyReader;
import view.MainMenuView;
import view.MenuView;
import view.PauseMenuView;

import java.io.IOException;

public class PauseState extends GameState {
    int option;     //option = 1 is Resume and option = 2 is Main Menu
    double start;
    private MenuView view;

    public PauseState(Game game, PauseMenuView view) {
        super(game);
        this.game.getPlayer().playSFX("MenuEffect");
        this.view = view;
        start = System.currentTimeMillis();
        option = 1;
    }

    @Override
    protected void draw() throws IOException {
        this.view.draw(game, option);
    }

    @Override
    protected void processKey(KeyReader.KEY key) {
        if (key == KeyReader.KEY.ARROWUP && option != 1) {
            this.game.getPlayer().playSFX("MenuEffect");
            option--;
            return;
        }

        if (key == KeyReader.KEY.ARROWDOWN && option != 2) {
            this.game.getPlayer().playSFX("MenuEffect");
            option++;
            return;
        }

        if (key == KeyReader.KEY.ENTER && option == 1) {
            this.game.getPlayer().playSFX("MenuSelectEffect");
            double incrementPause = System.currentTimeMillis() - start;
            game.getSurface().getModel().incrementPauseTime(incrementPause);
            game.setState(new PlayState(game, game.getSurface().getView()));
            return;
        }

        if (key == KeyReader.KEY.ENTER && option == 2) {
            this.game.getPlayer().playSFX("MenuSelectEffect");
            game.setState(new MenuState(game, new MainMenuView()));
        }
    }

    @Override
    public boolean run() {
        try {
            KeyReader.KEY key;
            KeyReader reader = new KeyReader(game.getScreen());
            this.draw();
            key = reader.readInput();
            this.processKey(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
