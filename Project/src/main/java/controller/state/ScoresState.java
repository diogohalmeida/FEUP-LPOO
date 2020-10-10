package controller.state;


import controller.Game;
import view.KeyReader;
import view.MainMenuView;
import view.MenuView;
import view.ScoresMenuView;


import java.io.IOException;

public class ScoresState extends GameState {
    int option;
    private MenuView view;

    public ScoresState(Game game, ScoresMenuView view) {
        super(game);
        this.view = view;
        game.getPlayer().setAudio("ScoresMusic");
        game.getPlayer().startMusic();
        this.option = 0;
    }

    @Override
    protected void draw() throws IOException {
        this.view.draw(game, option);
    }

    @Override
    protected void processKey(KeyReader.KEY key) {
        if ((key == KeyReader.KEY.ENTER && option == 0) || key == KeyReader.KEY.ESC) {
            this.game.getPlayer().playSFX("MenuSelectEffect");
            game.getPlayer().stopMusic();
            game.setState(new MenuState(game, new MainMenuView()));
            return;
        }
        if (key == KeyReader.KEY.ARROWDOWN && option > 0){
            this.game.getPlayer().playSFX("MenuEffect");
            option--;
            return;
        }
        if (key == KeyReader.KEY.ARROWUP && option < 5){
            this.game.getPlayer().playSFX("MenuEffect");
            option++;
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
