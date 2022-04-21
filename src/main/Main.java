package main;

import java.io.IOException;

import javax.swing.JFrame;

import main.settings.ReadPropertyFile;
import main.settings.Config;

import main.FrameController;
import main.parser.Beatmap;

public class Main {

    static FrameController frameController;
    static Conductor conductor;
    public static void main(String[] args){

        System.out.println("Initializing");

        Config config = new Config();
        ExtractBeatmaps readfile = new ExtractBeatmaps();
        //Beatmap beatmap = new Beatmap("src\\main\\temp_beatmaps\\Rachie - Thought Crime ([Aero]) [Icun's Hard].osu");

        frameController = new FrameController(config);
    }
}