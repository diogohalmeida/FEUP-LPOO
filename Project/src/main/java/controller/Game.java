package controller;

import com.googlecode.lanterna.screen.Screen;
import controller.state.GameState;
import model.Score;
import view.SoundPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    private Screen screen;
    private SoundPlayer player;
    private Surface surface;
    private GameState state;
    private ArrayList<Score> scores = new ArrayList<>();
    private int type;


    public Game(Screen screen, SoundPlayer player){
        this.type = 1;
        this.player = player;
        this.screen = screen;
    }

    public void run() throws IOException{
        readScores();
        while(true){
            if (!state.run()){
                break;
            }
        }

        screen.close();
        player.stopMusic();
        updateScoresFile();
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public Screen getScreen() {
        return screen;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    private void readScores() {
        try {
            File scoresFile = new File("./src/main/resources/scores.txt");
            Scanner fileReader = new Scanner(scoresFile);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                lineToScore(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void lineToScore(String line){
        String[] items = line.split(" , ");
        Score score = new Score(Double.parseDouble(items[0]),Integer.parseInt(items[1]),items[2]);
        scores.add(score);
    }

    private void updateScoresFile() {
        try {
            FileWriter myWriter = new FileWriter("./src/main/resources/scores.txt");
            for (Score score : scores) {
                myWriter.write(score.toString());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SoundPlayer getPlayer() {
        return player;
    }
}