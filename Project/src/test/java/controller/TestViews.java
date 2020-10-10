package controller;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.*;
import org.junit.Test;
import org.mockito.Mockito;
import view.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class TestViews {
    @Test
    public void testSurfaceView(){
        SurfaceView surfaceView = new SurfaceView();
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        SurfaceModel model = new SurfaceModel(80,24);
        Buggy buggy = new Buggy();
        model.setBuggy(buggy);
        surfaceView.draw(graphicsMock,model);

        verify(graphicsMock, times(1)).setBackgroundColor(TextColor.Factory.fromString("#061639"));
        verify(graphicsMock, times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getWidth(), model.getHeight()), ' ');
        verify(graphicsMock, times(2)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        //drawFloor
        int count = 0;
        for (boolean x: model.getFloor()){
            if (x)
                verify(graphicsMock, times(1)).putString(new TerminalPosition(79-count, 22), "#");
            else
                verify(graphicsMock, times(1)).putString(new TerminalPosition(79-count, 22), " ");

            count++;
        }

        for (int i = 0; i < 80; i++){
            verify(graphicsMock, times(1)).putString(new TerminalPosition(i, 23), "#");
        }

        verify(graphicsMock, times(2)).enableModifiers(SGR.BOLD);
        for (int i = 0; i < 16; i++){
            verify(graphicsMock, times(1)).putString(i, 3, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
        }

        for (int i = 0 ; i < 3; i++) {
            verify(graphicsMock, times(1)).putString(16, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
        }
        verify(graphicsMock, times(1)).putString(16, 3, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_RIGHT_CORNER));
        verify(graphicsMock, times(1)).putString(1, 1, "Time:  " + model.getCurrentTime());
        verify(graphicsMock, times(1)).putString(1, 2, "Kills: " + model.getEnemiesKilled());
        verify(graphicsMock, times(2)).disableModifiers(SGR.BOLD);
    }

    @Test
    public void testBuggyView(){
        BuggyView buggyView = new BuggyView();
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        BuggyModel model = new BuggyModel();

        //First buggy
        model.setType(1);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "(|)-(|)");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "0mm");

        model.setAnimPos(1);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "(\\)-(\\)");
        verify(graphicsMock, times(2)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "0mm");

        model.setAnimPos(2);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "(-)-(-)");
        verify(graphicsMock, times(3)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "0mm");

        model.setAnimPos(3);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "(/)-(/)");
        verify(graphicsMock, times(4)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "0mm");

        model.setJumping(true);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "(U)-(U)");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "oMm");


        //Second buggy
        model.setType(2);
        model.setJumping(false);
        model.setAnimPos(0);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "|=\\x/=|");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<o>");

        model.setAnimPos(1);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "|=|X|=|");
        verify(graphicsMock, times(2)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<o>");

        model.setAnimPos(2);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "|-\\X/=|");
        verify(graphicsMock, times(3)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<o>");

        model.setAnimPos(3);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "|=|x|-|");
        verify(graphicsMock, times(4)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<o>");

        model.setJumping(true);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "|U\\o/U|");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "<x>");


        //Third buggy
        model.setType(3);
        model.setJumping(false);
        model.setAnimPos(0);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "\\o/|\\o/");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|_|");

        model.setAnimPos(1);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "\\O/|\\O/");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|-|");

        model.setAnimPos(2);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "\\o/_\\o/");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|^|");

        model.setAnimPos(3);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "\\O/-\\O/");
        verify(graphicsMock, times(2)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|_|");

        model.setJumping(true);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "\\^/-\\^/");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "|*|");


        //Destroyed
        model.setDestroyed(true);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), "cn0MMnb");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY() - 1, "--");

        //Test laser
        ArrayList<Laser> lasers = new ArrayList<>();
        lasers.add(new Laser(model.getPosition(), 0));
        model.setLasers(lasers);
        buggyView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(new TerminalPosition(lasers.get(0).getPosition().getX(), lasers.get(0).getPosition().getY()), "--");


        //Test colors and modifiers
        verify(graphicsMock, times(17)).enableModifiers(SGR.BOLD);

        verify(graphicsMock, times(5)).setForegroundColor(TextColor.Factory.fromString("#00FFFF"));
        verify(graphicsMock, times(5)).setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        verify(graphicsMock, times(7)).setForegroundColor(TextColor.Factory.fromString("#FF00FF"));

        verify(graphicsMock, times(17)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        verify(graphicsMock, times(17)).disableModifiers(SGR.BOLD);
    }


    @Test
    public void testAlienView(){
        AlienView alienView = new AlienView();
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        AlienModel model = new AlienModel();

        ArrayList<Laser> lasers = new ArrayList<>();
        lasers.add(new Laser(model.getPosition(), 0));
        model.setLasers(lasers);

        alienView.draw(graphicsMock, model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY() - 2, "_ \\_");
        verify(graphicsMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#3CD070"));
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+1, model.getPosition().getY() - 2, "O");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY()-1, "\\__/");
        verify(graphicsMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), " '' ");
        verify(graphicsMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#DC143C"));
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+1, model.getPosition().getY()-5, Integer.toString(model.getHp()));

        verify(graphicsMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(graphicsMock, times(1)).putString(new TerminalPosition(lasers.get(0).getPosition().getX(), lasers.get(0).getPosition().getY()), "-");

        verify(graphicsMock, times(3)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(graphicsMock, times(1)).enableModifiers(SGR.BOLD);
        verify(graphicsMock, times(1)).disableModifiers(SGR.BOLD);
    }


    @Test
    public void testRobotView(){
        RobotView robotView = new RobotView();
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        RobotModel model = new RobotModel();

        robotView.draw(graphicsMock,model);
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY()-3, "\\__/");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY()-2, "|  |");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY()-1, "|__|");
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+1, model.getPosition().getY()-2, "oo");
        verify(graphicsMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        verify(graphicsMock, times(1)).putString(model.getPosition().getX(), model.getPosition().getY(), " '' ");
        verify(graphicsMock, times(2)).setForegroundColor(TextColor.Factory.fromString("#DC143C"));
        verify(graphicsMock, times(1)).putString(model.getPosition().getX()+2, model.getPosition().getY()-5, Integer.toString(model.getHp()));

        verify(graphicsMock, times(2)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(graphicsMock, times(1)).enableModifiers(SGR.BOLD);
        verify(graphicsMock, times(1)).disableModifiers(SGR.BOLD);
    }


    @Test
    public void testBuggySelectionMenuView() throws IOException {
        BuggySelectionMenuView buggySelectionMenuView = new BuggySelectionMenuView();
        Screen screenMock = Mockito.mock(Screen.class);
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Game game = new Game(screenMock, soundMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        buggySelectionMenuView.draw(game, 1);
        buggySelectionMenuView.draw(game, 2);
        buggySelectionMenuView.draw(game, 3);
        buggySelectionMenuView.draw(game, 4);

        verify(graphicsMock, times(4)).setBackgroundColor(TextColor.Factory.fromString("#061639"));
        verify(graphicsMock, times(4)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');
        verify(graphicsMock, times(24)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));


        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 1), "MM     MM  OOOOO   OOOOO  NN     N   BBBBBB  U     U  GGGGG   GGGGG  Y     Y");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 2), "M M   M M O     O O     O N N    N   B     B U     U G     G G     G  Y   Y");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 3), "M  M M  M O     O O     O N  N   N   BBBBBB  U     U G       G         Y Y");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 4), "M   M   M O     O O     O N   N  N   B     B U     U G   GGG G   GGG    Y ");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 5), "M       M O     O O     O N    N N   B     B U     U G     G G     G    Y");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 6), "M       M  OOOOO   OOOOO  N     NN   BBBBBB   UUUUU   GGGGG   GGGGG     Y");


        verify(graphicsMock, times(8)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(graphicsMock, times(4)).putString(new TerminalPosition(30, 8), "When Gravity Fails!");

        for (int i = 0; i < 80; i++) {
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 23), "#");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 22), "#");
        }


        verify(graphicsMock, times(1)).putString(new TerminalPosition(8, 18), "It never breaks down!");
        verify(graphicsMock, times(1)).putString(new TerminalPosition(16, 19), "Never");
        verify(graphicsMock, times(4)).putString(15, 14, "(|)-(|)");
        verify(graphicsMock, times(4)).putString(17, 13, "0mm");
        verify(graphicsMock, times(4)).setForegroundColor(TextColor.Factory.fromString("#00FFFF"));
        verify(graphicsMock, times(4)).putString(new TerminalPosition(15, 16), "Classic");


        verify(graphicsMock, times(1)).putString(new TerminalPosition(32, 18), "The future's LRV");
        verify(graphicsMock, times(4)).putString(36, 14, "|=\\x/=|");
        verify(graphicsMock, times(4)).putString(38, 13, "<o>");
        verify(graphicsMock, times(4)).setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        verify(graphicsMock, times(4)).putString(new TerminalPosition(36, 16), "ApolloX");


        verify(graphicsMock, times(1)).putString(new TerminalPosition(54, 18), "SpaceY's CEO");
        verify(graphicsMock, times(1)).putString(new TerminalPosition(53, 19), "favorite robot?");
        verify(graphicsMock, times(4)).putString(57, 14, "\\o/|\\o/");
        verify(graphicsMock, times(4)).putString(59, 13, "|_|");
        verify(graphicsMock, times(4)).setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
        verify(graphicsMock, times(4)).putString(new TerminalPosition(56, 16), "X AE A-12");


        verify(graphicsMock, times(4)).putString(35, 20, "Main Menu");
        verify(graphicsMock, times(4)).enableModifiers(SGR.BLINK);


        verify(screenMock, times(4)).refresh();
    }


    @Test
    public void testGameOverMenuView() throws IOException {
        GameOverView gameOverView = new GameOverView();
        Screen screenMock = Mockito.mock(Screen.class);
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Game game = new Game(screenMock, soundMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        gameOverView.draw(game);
        verify(graphicsMock, times(1)).setBackgroundColor(TextColor.Factory.fromString("#061639"));
        verify(graphicsMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        verify(graphicsMock, times(1)).enableModifiers(SGR.BOLD);
        verify(graphicsMock, times(1)).putString(27, 17, "Press ENTER to continue");
        verify(graphicsMock, times(1)).disableModifiers(SGR.BLINK);
        verify(graphicsMock, times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(graphicsMock, times(1)).putString(34, 15, "GAME OVER");

        verify(graphicsMock, times(1)).enableModifiers(SGR.BLINK);

        verify(screenMock, times(1)).refresh();
    }


    @Test
    public void testMainMenuView() throws IOException {
        MainMenuView mainMenuView = new MainMenuView();
        Screen screenMock = Mockito.mock(Screen.class);
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Game game = new Game(screenMock, soundMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        mainMenuView.draw(game,1);
        game.setType(2);
        mainMenuView.draw(game,2);
        game.setType(3);
        mainMenuView.draw(game,3);
        mainMenuView.draw(game,4);

        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 1), "MM     MM  OOOOO   OOOOO  NN     N   BBBBBB  U     U  GGGGG   GGGGG  Y     Y");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 2), "M M   M M O     O O     O N N    N   B     B U     U G     G G     G  Y   Y");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 3), "M  M M  M O     O O     O N  N   N   BBBBBB  U     U G       G         Y Y");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 4), "M   M   M O     O O     O N   N  N   B     B U     U G   GGG G   GGG    Y ");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 5), "M       M O     O O     O N    N N   B     B U     U G     G G     G    Y");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(2, 6), "M       M  OOOOO   OOOOO  N     NN   BBBBBB   UUUUU   GGGGG   GGGGG     Y");

        verify(graphicsMock, times(4)).enableModifiers(SGR.BLINK);
        verify(graphicsMock, times(8)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(graphicsMock, times(4)).putString(new TerminalPosition(30, 8), "When Gravity Fails!");
        verify(graphicsMock, times(12)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(graphicsMock, times(8)).disableModifiers(SGR.BOLD);
        verify(graphicsMock, times(4)).disableModifiers(SGR.BLINK);

        for (int i = 0; i < 80; i++) {
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 23), "#");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 22), "#");
        }

        for (int i = 28; i <51; i++){
            verify(graphicsMock, times(4)).putString(i, 19, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
            verify(graphicsMock, times(4)).putString(i, 9, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
        }

        for (int i = 10 ; i < 19; i++) {
            verify(graphicsMock, times(4)).putString(27, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
            verify(graphicsMock, times(4)).putString(51, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
        }

        verify(graphicsMock, times(4)).putString(50, 19, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_RIGHT_CORNER));
        verify(graphicsMock, times(4)).putString(28, 19, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_LEFT_CORNER));
        verify(graphicsMock, times(4)).putString(50, 9, String.valueOf(Symbols.SINGLE_LINE_TOP_RIGHT_CORNER));
        verify(graphicsMock, times(4)).putString(28, 9, String.valueOf(Symbols.SINGLE_LINE_TOP_LEFT_CORNER));


        verify(graphicsMock, times(4)).putString(new TerminalPosition(32, 15), "SCORES");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(32, 13), "BUGGY SELECTION");
        verify(graphicsMock, times(4)).putString(new TerminalPosition(32, 17), "EXIT GAME");
        verify(graphicsMock, times(8)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(graphicsMock, times(4)).putString(new TerminalPosition(32, 11), "PLAY");

        for (int i = 9; i <= 61; ) {
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 10), "IIIIIIIII");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 11), "II     II");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 12), "III   III");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 13), " II   II ");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 14), " II   II ");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 15), " II   II ");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 16), "III   III");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 17), "II     II");
            verify(graphicsMock, times(4)).putString(new TerminalPosition(i, 18), "IIIIIIIII");

            i += 52;
        }

        verify(graphicsMock, times(8)).setBackgroundColor(TextColor.Factory.fromString("#061639"));
        verify(graphicsMock, times(1)).putString(36, 21, "(|)-(|)");
        verify(graphicsMock, times(1)).putString(38, 20, "0mm");


        verify(graphicsMock, times(1)).putString(36, 21, "|=\\x/=|");
        verify(graphicsMock, times(1)).putString(38, 20, "<o>");


        verify(graphicsMock, times(2)).putString(36, 21, "\\o/|\\o/");
        verify(graphicsMock, times(2)).putString(38, 20, "|_|");

        verify(screenMock, times(4)).refresh();

    }


    @Test
    public void testPauseMenuView() throws IOException {
        PauseMenuView pauseMenuView = new PauseMenuView();
        Screen screenMock = Mockito.mock(Screen.class);
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Game game = new Game(screenMock, soundMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        pauseMenuView.draw(game,1);
        pauseMenuView.draw(game,2);

        verify(graphicsMock, times(2)).setBackgroundColor(TextColor.Factory.fromString("#061639"));
        verify(graphicsMock, times(2)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');
        verify(graphicsMock, times(2)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        for (int i = 0; i < 80; i++) {
            verify(graphicsMock, times(2)).putString(new TerminalPosition(i, 23), "#");
        }

        verify(graphicsMock, times(2)).enableModifiers(SGR.BOLD);
        for (int i = 31; i < 48; i++){
            verify(graphicsMock, times(2)).putString(i, 16, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
            verify(graphicsMock, times(2)).putString(i, 10, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
            verify(graphicsMock, times(2)).putString(i, 6, String.valueOf(Symbols.SINGLE_LINE_HORIZONTAL));
        }

        for (int i = 7 ; i < 16; i++) {
            verify(graphicsMock, times(2)).putString(30, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
            verify(graphicsMock, times(2)).putString(48, i, String.valueOf(Symbols.SINGLE_LINE_VERTICAL));
        }
        verify(graphicsMock, times(2)).putString(31, 16, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_LEFT_CORNER));
        verify(graphicsMock, times(2)).putString(47, 16, String.valueOf(Symbols.SINGLE_LINE_BOTTOM_RIGHT_CORNER));
        verify(graphicsMock, times(2)).putString(31, 6, String.valueOf(Symbols.SINGLE_LINE_TOP_LEFT_CORNER));
        verify(graphicsMock, times(2)).putString(47, 6, String.valueOf(Symbols.SINGLE_LINE_TOP_RIGHT_CORNER));


        verify(graphicsMock, times(2)).putString(new TerminalPosition(34, 8), "GAME PAUSED");

        verify(graphicsMock, times(2)).putString(new TerminalPosition(35, 14), "MAIN MENU");
        verify(graphicsMock, times(2)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(graphicsMock, times(2)).putString(new TerminalPosition(35, 12), "RESUME");

        verify(screenMock, times(2)).refresh();
    }



    @Test
    public void testScoresMenuView() throws IOException {
        ScoresMenuView scoresMenuView = new ScoresMenuView();
        Screen screenMock = Mockito.mock(Screen.class);
        SoundPlayer soundMock = Mockito.mock(SoundPlayer.class);
        Game game = new Game(screenMock, soundMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        scoresMenuView.draw(game, 0);
        game.setType(2);
        scoresMenuView.draw(game, 1);
        game.setType(3);
        scoresMenuView.draw(game, 2);
        scoresMenuView.draw(game, 3);
        scoresMenuView.draw(game, 4);
        scoresMenuView.draw(game, 5);

        verify(graphicsMock, times(12)).setBackgroundColor(TextColor.Factory.fromString("#061639"));
        verify(graphicsMock, times(6)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(80, 24), ' ');
        verify(graphicsMock, times(18)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        verify(graphicsMock, times(6)).putString(new TerminalPosition(2, 1), "MM     MM  OOOOO   OOOOO  NN     N   BBBBBB  U     U  GGGGG   GGGGG  Y     Y");
        verify(graphicsMock, times(6)).putString(new TerminalPosition(2, 2), "M M   M M O     O O     O N N    N   B     B U     U G     G G     G  Y   Y");
        verify(graphicsMock, times(6)).putString(new TerminalPosition(2, 3), "M  M M  M O     O O     O N  N   N   BBBBBB  U     U G       G         Y Y");
        verify(graphicsMock, times(6)).putString(new TerminalPosition(2, 4), "M   M   M O     O O     O N   N  N   B     B U     U G   GGG G   GGG    Y ");
        verify(graphicsMock, times(6)).putString(new TerminalPosition(2, 5), "M       M O     O O     O N    N N   B     B U     U G     G G     G    Y");
        verify(graphicsMock, times(6)).putString(new TerminalPosition(2, 6), "M       M  OOOOO   OOOOO  N     NN   BBBBBB   UUUUU   GGGGG   GGGGG     Y");

        verify(graphicsMock, times(6)).enableModifiers(SGR.BLINK);
        verify(graphicsMock, times(7)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(graphicsMock, times(6)).putString(new TerminalPosition(30, 8), "When Gravity Fails!");
        verify(graphicsMock, times(12)).disableModifiers(SGR.BOLD);
        verify(graphicsMock, times(6)).disableModifiers(SGR.BLINK);

        for (int i = 0; i < 80; i++) {
            verify(graphicsMock, times(6)).putString(new TerminalPosition(i, 23), "#");
            verify(graphicsMock, times(6)).putString(new TerminalPosition(i, 22), "#");
        }

        verify(graphicsMock, times(18)).enableModifiers(SGR.BOLD);

        verify(graphicsMock, times(1)).putString(36, 21, "(|)-(|)");
        verify(graphicsMock, times(1)).putString(38, 20, "0mm");


        verify(graphicsMock, times(1)).putString(36, 21, "|=\\x/=|");
        verify(graphicsMock, times(1)).putString(38, 20, "<o>");


        verify(graphicsMock, times(4)).putString(36, 21, "\\o/|\\o/");
        verify(graphicsMock, times(4)).putString(38, 20, "|_|");


        verify(graphicsMock, times(6)).putString(new TerminalPosition(24, 10), "Rank  Time       Kills  Date");

        verify(graphicsMock, times(6)).putString(new TerminalPosition(35, 18), "Main Menu");

        verify(screenMock, times(6)).refresh();

    }
}
