package main;

import java.io.IOException;

import javax.swing.JFrame;

import main.settings.ReadPropertyFile;
import main.settings.Config;

import main.FrameController;
// import main.parser.BeatmapParser;

public class Main {

    static FrameController frameController;
    static Conductor conductor;
    public static void main(String[] args){
        System.out.println("Hello World");

        // startScreen = new StartScreen();
        Config config = new Config();
        ExtractBeatmaps readfile = new ExtractBeatmaps();
        // BeatmapParser beatmap = new BeatmapParser("src\\main\\temp_beatmaps\\Rachie - Thought Crime ([Aero]) [Icun's Hard].osu");

        frameController = new FrameController(config);

        // System.out.println(config.getX());
    }
}