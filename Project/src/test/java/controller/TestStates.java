package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import controller.state.*;
import model.BuggyModel;
import org.junit.Test;
import org.mockito.Mockito;
import view.*;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestStates {

    @Test
    public void testBuggySelectionState() throws IOException {
        //Choose the first buggy
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Screen screenMock = Mockito.mock(Screen.class);
        Game game = new Game(screenMock, soundMock);
        BuggySelectionMenuView buggySelectionMenuViewMock = Mockito.mock(BuggySelectionMenuView.class);
        GameState state = new BuggySelectionState(game, buggySelectionMenuViewMock);
        game.setState(state);

        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();

        assertEquals(1, game.getType());
        assertEquals(MenuState.class, game.getState().getClass());


        //Choose the second buggy
        game = new Game(screenMock, soundMock);
        state = new BuggySelectionState(game, buggySelectionMenuViewMock);
        game.setState(state);

        doReturn(new KeyStroke(KeyType.ArrowRight, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();

        assertEquals(2, game.getType());
        assertEquals(MenuState.class, game.getState().getClass());


        //Choose the third buggy
        game = new Game(screenMock, soundMock);
        state = new BuggySelectionState(game, buggySelectionMenuViewMock);
        game.setState(state);

        doReturn(new KeyStroke(KeyType.ArrowRight, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowRight, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();

        assertEquals(3, game.getType());
        assertEquals(MenuState.class, game.getState().getClass());


        //Exit
        game = new Game(screenMock, soundMock);
        state = new BuggySelectionState(game, buggySelectionMenuViewMock);
        game.setState(state);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();

        assertEquals(1, game.getType());
        assertEquals(MenuState.class, game.getState().getClass());

        verify(soundMock, times(4)).playSFX("MenuEffect");
        verify(soundMock, times(4)).playSFX("MenuSelectEffect");
        verify(soundMock, times(8)).startMusic();
        verify(soundMock, times(4)).stopMusic();
    }


    @Test
    public void testGameOverState() throws IOException {
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Screen screenMock = Mockito.mock(Screen.class);
        Game game = new Game(screenMock, soundMock);
        GameOverView gameOverViewMock = Mockito.mock(GameOverView.class);
        GameState state = new GameOverState(game, gameOverViewMock);
        game.setState(state);
        Surface surface = new Surface(80,24);
        game.setSurface(surface);

        //Press enter after Game over
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();

        assertEquals(MenuState.class, game.getState().getClass());

        verify(soundMock, times(1)).playSFX("GameOverEffect");
        verify(soundMock, times(1)).playSFX("MenuSelectEffect");
        verify(soundMock, times(1)).startMusic();
        verify(soundMock, times(1)).stopMusic();

    }


    @Test
    public void testMainMenuState() throws IOException {
        //Enter play
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Screen screenMock = Mockito.mock(Screen.class);
        Game game = new Game(screenMock, soundMock);
        MainMenuView menuViewMock = Mockito.mock(MainMenuView.class);
        GameState state = new MenuState(game, menuViewMock);
        game.setState(state);

        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();

        assertEquals(PlayState.class, game.getState().getClass());


        //Enter buggy selection
        game = new Game(screenMock, soundMock);
        state = new MenuState(game, menuViewMock);
        game.setState(state);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();

        assertEquals(BuggySelectionState.class, game.getState().getClass());


        //Enter scores
        game = new Game(screenMock, soundMock);
        state = new MenuState(game, menuViewMock);
        game.setState(state);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowUp, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();

        assertEquals(ScoresState.class, game.getState().getClass());


        //Exit
        game = new Game(screenMock, soundMock);
        state = new MenuState(game, menuViewMock);
        game.setState(state);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        assertFalse(state.run());

        verify(soundMock, times(8)).playSFX("MenuEffect");
        verify(soundMock, times(3)).playSFX("MenuSelectEffect");
        verify(soundMock, times(6)).startMusic();
        verify(soundMock, times(3)).stopMusic();
    }

    @Test
    public void testPauseState() throws IOException {
        //Resume game
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Screen screenMock = Mockito.mock(Screen.class);
        Game game = new Game(screenMock, soundMock);
        PauseMenuView pauseMenuViewMock = Mockito.mock(PauseMenuView.class);
        PauseState state = new PauseState(game, pauseMenuViewMock);
        game.setState(state);
        Surface surface = new Surface(80,24);
        game.setSurface(surface);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowUp, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();
        assertEquals(PlayState.class, game.getState().getClass());

        //Main menu
        game = new Game(screenMock, soundMock);
        state = new PauseState(game, pauseMenuViewMock);
        game.setState(state);
        game.setSurface(surface);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();
        assertEquals(MenuState.class, game.getState().getClass());

        verify(soundMock, times(5)).playSFX("MenuEffect");
        verify(soundMock, times(2)).playSFX("MenuSelectEffect");
        verify(soundMock, times(1)).startMusic();
    }


    @Test
    public void testPlayState() throws IOException{
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Screen screenMock = Mockito.mock(Screen.class);
        Game game = new Game(screenMock, soundMock);

        Surface surface = new Surface(80, 24);
        SurfaceView surfaceViewMock = Mockito.mock(SurfaceView.class);
        Buggy buggyMock = Mockito.mock(Buggy.class);
        game.setSurface(surface);
        game.getSurface().getModel().setBuggy(buggyMock);

        GameState state = new PlayState(game, surfaceViewMock);
        game.setState(state);

        //Jump
        doReturn(new KeyStroke(' ', false, false)).when(screenMock).pollInput();
        doReturn(new BuggyModel()).when(buggyMock).getModel();
        state.run();
        verify(buggyMock, times(1)).jump();

        //Shoot Left
        doReturn(new KeyStroke(KeyType.ArrowLeft, false, false)).when(screenMock).pollInput();
        doReturn(new BuggyModel()).when(buggyMock).getModel();
        state.run();
        verify(buggyMock, times(1)).shoot(0);

        //Shoot Right
        doReturn(new KeyStroke(KeyType.ArrowRight, false, false)).when(screenMock).pollInput();
        doReturn(new BuggyModel()).when(buggyMock).getModel();
        state.run();
        verify(buggyMock, times(1)).shoot(1);

        //Pause
        doReturn(new KeyStroke(KeyType.Escape, false, false)).when(screenMock).pollInput();
        state.run();
        assertEquals(PauseState.class, game.getState().getClass());

        //If the buggy is destroyed
        game = new Game(screenMock, soundMock);
        surface = new Surface(80, 24);
        game.setSurface(surface);
        Buggy buggy = new Buggy();
        buggy.getModel().setDestroyed(true);
        surface.getModel().setBuggy(buggy);
        state = new PlayState(game, surfaceViewMock);

        state.run();
        assertEquals(GameOverState.class, game.getState().getClass());

        verify(soundMock, times(1)).playSFX("JumpEffect");
        verify(soundMock, times(2)).playSFX("LaserEffect");
        verify(soundMock, times(2)).stopMusic();

    }


    @Test
    public void testScoresState() throws IOException{
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Screen screenMock = Mockito.mock(Screen.class);
        Game game = new Game(screenMock, soundMock);
        ScoresMenuView scoresMenuViewMock = Mockito.mock(ScoresMenuView.class);
        ScoresState state = new ScoresState(game, scoresMenuViewMock);
        game.setState(state);

        doReturn(new KeyStroke(KeyType.ArrowUp, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowUp, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowUp, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowUp, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowUp, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        state.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        state.run();

        assertEquals(MenuState.class, game.getState().getClass());

        verify(soundMock, times(10)).playSFX("MenuEffect");
        verify(soundMock, times(1)).playSFX("MenuSelectEffect");
        verify(soundMock, times(2)).startMusic();
        verify(soundMock, times(1)).stopMusic();
    }
}
