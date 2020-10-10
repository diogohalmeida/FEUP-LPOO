package controller;

import controller.state.GameState;
import controller.state.MenuState;
import org.junit.Test;
import org.mockito.Mockito;
import com.googlecode.lanterna.screen.Screen;
import view.SoundPlayer;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class TestGame {
    @Test
    public void testRun() throws IOException {
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Screen screenMock = Mockito.mock(Screen.class);
        Game game = new Game(screenMock, soundMock);

        GameState stateMock = Mockito.mock(MenuState.class);
        game.setState(stateMock);
        game.run();

        doReturn(false).when(stateMock).run();
        verify(stateMock, times(1)).run();
        verify(screenMock,times(1)).close();
        verify(soundMock,times(1)).stopMusic();
    }
}
