package main;

import java.util.logging.Logger;
import java.util.logging.Level;

import main.settings.Config;

public class Main {

    public static FrameController frameController;
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args){

        logger.log(Level.INFO, "Initializing");

        Config config = new Config();
        ExtractBeatmaps.main();

        frameController = new FrameController(config);
    }
}