package controller.state;

import controller.Game;
import view.KeyReader;

import java.io.IOException;

public abstract class GameState {
    protected Game game;

    public GameState(Game game){
        this.game = game;
    }

    protected abstract void draw() throws IOException;

    protected abstract void processKey(KeyReader.KEY key);

    public abstract boolean run();


}
