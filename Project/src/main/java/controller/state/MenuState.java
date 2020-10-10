package controller.state;

import controller.Buggy;
import controller.Game;
import controller.Surface;
import view.*;

import java.io.IOException;

public class MenuState extends GameState {
    int option;
    private MenuView view;

    public MenuState(Game game, MainMenuView view) {
        super(game);
        this.view = view;
        game.getPlayer().setAudio("MenuMusic");
        game.getPlayer().startMusic();
        this.option = 1;
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

        if (key == KeyReader.KEY.ARROWDOWN && option != 4) {
            this.game.getPlayer().playSFX("MenuEffect");
            option++;
            return;
        }

        if (key == KeyReader.KEY.ENTER && option == 1) {
            game.getPlayer().stopMusic();
            this.game.getPlayer().playSFX("MenuSelectEffect");
            startGame();
            game.setState(new PlayState(game, game.getSurface().getView()));
            return;
        }

        if (key == KeyReader.KEY.ENTER && option == 2) {
            this.game.getPlayer().playSFX("MenuSelectEffect");
            game.getPlayer().stopMusic();
            game.setState(new BuggySelectionState(game, new BuggySelectionMenuView()));
        }


        if (key == KeyReader.KEY.ENTER && option == 3) {
            this.game.getPlayer().playSFX("MenuSelectEffect");
            game.getPlayer().stopMusic();
            game.setState(new ScoresState(game, new ScoresMenuView()));
        }

    }

    @Override
    public boolean run(){
        try {
            KeyReader.KEY key;
            KeyReader reader = new KeyReader(game.getScreen());
            this.draw();
            key = reader.readInput();
            this.processKey(key);
            if (option == 4 && key == KeyReader.KEY.ENTER){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void startGame(){
        this.game.setSurface(new Surface(80,24));
        Buggy buggy = new Buggy();
        buggy.getModel().setType(game.getType());
        this.game.getSurface().getModel().setBuggy(buggy);
    }



}
