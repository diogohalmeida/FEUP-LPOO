package view;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
    private Clip music;

    private Map<String, Clip> musics;

    public SoundPlayer() {

        this.musics = new HashMap<>();

        loadMusic("MenuMusic");
        loadMusic("SelectionMusic");
        loadMusic("ScoresMusic");
    }

    public void loadMusic(String name) {
        try {
            File musicFile = new File("./sound/soundtrack/" + name + ".wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioInput);
            musics.put(name, audioClip);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAudio(String name){
        music = musics.get(name);
    }

    public void startMusic(){
        music.setMicrosecondPosition(0);
        music.start();
        music.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic(){
        if (music != null){
            music.stop();
        }
    }

    public void playSFX(String name) {
        try {
            File musicFile = new File("./sound/soundfx/" + name + ".wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioInput);
            musics.put(name, audioClip);
            audioClip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

