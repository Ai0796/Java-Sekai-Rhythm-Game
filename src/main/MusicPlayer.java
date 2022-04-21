package main;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

//Copied from https://www.delftstack.com/howto/java/java-play-mp3/
//I do not claim ownership of this code
class MusicPlayer{

    private Player jlPlayer;
    private String path;

    public MusicPlayer(String path) {
        this.path = path;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void play() {
        new Thread() {
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