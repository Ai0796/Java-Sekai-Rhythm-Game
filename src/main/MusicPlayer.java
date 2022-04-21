package main;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

//Copied from https://www.delftstack.com/howto/java/java-play-mp3/
//I do not claim ownership of this code
class MusicPlayer{

    private Player jlPlayer;

    public MusicPlayer(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (JavaLayerException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void play() {
        new Thread() {
            @Override
            public void run() {
                try {
                    jlPlayer.play();
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }
            }
        }.start();

    }

    public void close() {
        if (jlPlayer != null)
            jlPlayer.close();
    }
}