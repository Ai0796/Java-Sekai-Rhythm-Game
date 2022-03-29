package main.gui;

import javax.swing.JFrame;

import main.Conductor;
import main.FrameController;
import main.Lane;
import main.settings.Config;

public class RhythmScreen implements InnerBaseGui{

    private JFrame frame;
    private Config config;
    private FrameController frameController;

    private static final double laneWidthRatio = 1.0 / 10.0;
    private Lane[] lanes;
    
    public RhythmScreen(JFrame frame, Config config, FrameController frameController){
        
        this.config = config;
        this.frame = frame;
        this.frameController = frameController;

        lanes = new Lane[config.getLanes()];

        for(int i = 0; i < config.getLanes(); i++){
            Lane lane = new Lane();
            lanes[i] = lane;
        }
    }

    public void startGame(){
        // Conductor conductor = new Conductor(bpm, finalPosition)
    }

    public void show(){
        this.frame.setVisible(true);
    }

    public void hide(){
        this.frame.setVisible(false);
    }
}
