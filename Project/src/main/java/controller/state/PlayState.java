package controller.state;

import controller.Game;
import view.GameOverView;
import view.KeyReader;
import view.PauseMenuView;
import view.SurfaceView;

import java.io.IOException;

public class PlayState extends GameState {
    private SurfaceView view;

    public PlayState(Game game, SurfaceView view) {
        super(game);
        this.view= view;
    }

    @Override
    protected void draw() throws IOException{
        game.getScreen().clear();
        view.draw(game.getScreen().newTextGraphics(),game.getSurface().getModel());
        game.getScreen().refresh();
    }

    @Override
    protected void processKey(KeyReader.KEY key) {
        if (key == KeyReader.KEY.NONE) {
            return;
        }

        if (key == KeyReader.KEY.SPACE && !game.getSurface().getModel().getBuggy().getModel().getJumping()) {
            this.game.getPlayer().playSFX("JumpEffect");
            game.getSurface().getModel().getBuggy().jump();
        }

        if (key == KeyReader.KEY.ARROWLEFT  ) {
            playShootingSFX();
            game.getSurface().getModel().getBuggy().shoot(0);
        }

        if (key == KeyReader.KEY.ARROWRIGHT) {
            playShootingSFX();
            game.getSurface().getModel().getBuggy().shoot(1);
        }

        if (key == KeyReader.KEY.P  || key == KeyReader.KEY.ESC) {
            game.getPlayer().stopMusic();
            game.setState(new PauseState(game, new PauseMenuView()));
        }
    }

    @Override
    public boolean run(){
        try {
            KeyReader.KEY key;
            KeyReader reader = new KeyReader(game.getScreen());
            if (game.getSurface().getModel().getBuggy().getModel().getDestroyed()){
                game.setState(new GameOverState(game, new GameOverView()));
                return true;
            }
            key = reader.pollInput();

            this.processKey(key);
            game.getSurface().processGame();
            this.draw();
            Thread.sleep(100);

        }
        catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        return true;
    }

    private void playShootingSFX(){
        if (game.getSurface().getModel().getBuggy().getModel().getLasers().size() < 3)
            game.getPlayer().playSFX("LaserEffect");
        else{
            game.getPlayer().playSFX("NoAmmoEffect");
        }
    }
}
