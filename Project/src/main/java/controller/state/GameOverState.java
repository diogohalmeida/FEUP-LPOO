package controller.state;
import controller.Game;
import model.Score;
import view.GameOverView;
import view.KeyReader;
import view.MainMenuView;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;


public class GameOverState extends GameState {
    private GameOverView view;

    public GameOverState(Game game,  GameOverView view) {
        super(game);
        this.view = view;
        game.getPlayer().stopMusic();
        game.getPlayer().playSFX("GameOverEffect");
    }

    @Override
    protected void draw() throws IOException {
        view.draw(game);
    }

    @Override
    protected void processKey(KeyReader.KEY key) {
        if (key == KeyReader.KEY.ENTER){
            this.game.getPlayer().playSFX("MenuSelectEffect");
            this.checkScore();
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

    private void checkScore(){
        Calendar calendar = Calendar.getInstance();
        String day = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
        String year = String.format("%04d", calendar.get(Calendar.YEAR));
        String date = day + "/" + month + "/" + year;

        Score newScore = new Score(game.getSurface().getModel().getCurrentTime(), game.getSurface().getModel().getEnemiesKilled(), date);

        if (game.getScores().size() == 5) {
            for (Score score : game.getScores()) {
                if (game.getSurface().getModel().getCurrentTime() > score.getTime()) {
                    game.getScores().remove(game.getScores().get(4));
                    game.getScores().add(newScore);
                    break;
                }
            }
        }
        else{
            game.getScores().add(newScore);
        }

        Collections.sort(game.getScores());
    }

}


