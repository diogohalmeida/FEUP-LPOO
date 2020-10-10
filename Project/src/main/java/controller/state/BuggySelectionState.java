package controller.state;

import controller.Game;
import view.BuggySelectionMenuView;
import view.KeyReader;
import view.MainMenuView;

import java.io.IOException;

public class BuggySelectionState extends GameState {
    private int option;
    private BuggySelectionMenuView view;

    public BuggySelectionState(Game game, BuggySelectionMenuView view) {
        super(game);
        this.view = view;
        game.getPlayer().setAudio("SelectionMusic");
        game.getPlayer().startMusic();
        this.option = 1;
    }

    @Override
    protected void draw() throws IOException {
        this.view.draw(game, option);
    }

    @Override
    protected void processKey(KeyReader.KEY key) {
        if (key == KeyReader.KEY.ARROWLEFT && option == 2) {
            this.game.getPlayer().playSFX("MenuEffect");
            this.option = 1;
            return;
        }

        if (key == KeyReader.KEY.ARROWRIGHT && option == 2) {
            this.game.getPlayer().playSFX("MenuEffect");
            this.option = 3;
            return;
        }

        if ((key == KeyReader.KEY.ARROWRIGHT && option == 1) || (key == KeyReader.KEY.ARROWLEFT && option == 3) || (key == KeyReader.KEY.ARROWUP && option == 4)) {
            this.game.getPlayer().playSFX("MenuEffect");
            option = 2;
            return;
        }

        if (key == KeyReader.KEY.ARROWDOWN) {
            this.game.getPlayer().playSFX("MenuEffect");
            option = 4;
            return;
        }

        if (key == KeyReader.KEY.ENTER && option == 1) {
            this.game.getPlayer().playSFX("MenuSelectEffect");
            game.getPlayer().stopMusic();
            this.game.setType(1);
            game.setState(new MenuState(game, new MainMenuView()));
        }

        if (key == KeyReader.KEY.ENTER && option == 2) {
            this.game.getPlayer().playSFX("MenuSelectEffect");
            game.getPlayer().stopMusic();
            this.game.setType(2);
            game.setState(new MenuState(game, new MainMenuView()));
        }

        if (key == KeyReader.KEY.ENTER && option == 3) {
            this.game.getPlayer().playSFX("MenuSelectEffect");
            game.getPlayer().stopMusic();
            this.game.setType(3);
            game.setState(new MenuState(game, new MainMenuView()));
        }

        if ((key == KeyReader.KEY.ENTER && option == 4) || (key == KeyReader.KEY.ESC)) {
            this.game.getPlayer().playSFX("MenuSelectEffect");
            game.getPlayer().stopMusic();
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
