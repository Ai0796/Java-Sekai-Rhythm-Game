package main.gui;

import javax.swing.JFrame;

import main.Conductor;
import main.FrameController;
import main.settings.Config;

public class RhythmScreen implements InnerBaseGui{

    private JFrame frame;
    private Config config;
    private FrameController frameController;
    
    public RhythmScreen(JFrame frame, Config config, FrameController frameController){
        
        this.config = config;
        this.frame = frame;
        this.frameController = frameController;

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
