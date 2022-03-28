package main.gui;

import javax.swing.JFrame;

import main.Conductor;
import main.settings.Config;

public class RhythmScreen implements InnerBaseGui{

    private JFrame frame;
    private Config config;
    
    public RhythmScreen(JFrame frame, Config config){
        
        this.config = config;
        this.frame = frame;
        
        frame.setLayout(null);
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

    public void setResizableTrue(){
        this.frame.setResizable(true);
    }

    public void setResizableFalse(){
        this.frame.setResizable(false);
    }
}
