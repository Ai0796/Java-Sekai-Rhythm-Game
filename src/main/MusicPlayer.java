package main;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.jlp;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

//Copied from https://www.delftstack.com/howto/java/java-play-mp3/
//I do not claim ownership of this code
public class MusicPlayer{

    private Player jlPlayer;
    private String path;

    public MusicPlayer(String path) {
        this.path = path;
    }

    public void play() {

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            this.jlPlayer = new Player(bufferedInputStream);
        } catch (JavaLayerException | IOException e) {
            // TODO Auto-generated catch block
            Main.logger.log(Level.WARNING, "File failed to load");
            e.printStackTrace();
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    Main.logger.log(Level.INFO, String.format("Music started playing at: %d", System.currentTimeMillis()));
                    jlPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void close() {
        if (jlPlayer != null){
            jlPlayer.close();
        }
    }

    public boolean isComplete()
    {
        if (jlPlayer != null) {
            return jlPlayer.isComplete();
        }

        //Default
        return false;
    }

    public long getPosition()
    {
        return jlPlayer.getPosition();
    }
}