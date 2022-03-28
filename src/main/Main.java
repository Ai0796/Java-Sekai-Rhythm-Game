package main;

import java.io.IOException;

import javax.swing.JFrame;

import main.settings.ReadPropertyFile;
import main.settings.Config;

import main.FrameController;

public class Main {

    static FrameController frameController;
    static Conductor conductor;
    public static void main(String[] args){
        System.out.println("Hello World");

        // startScreen = new StartScreen();
        Config config = new Config();
        ExtractBeatmaps readfile = new ExtractBeatmaps();

        frameController = new FrameController(config);

        // System.out.println(config.getX());
    }
}