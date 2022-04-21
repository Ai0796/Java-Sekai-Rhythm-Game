package main;

import main.settings.Config;

public class Main {

    static FrameController frameController;
    static Conductor conductor;
    public static void main(String[] args){

        System.out.println("Initializing");

        Config config = new Config();
        ExtractBeatmaps.main();
        frameController = new FrameController(config);
    }
}