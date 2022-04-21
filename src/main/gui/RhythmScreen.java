package main.gui;

import javax.swing.JFrame;

import main.Lane;
import main.settings.Config;

public class RhythmScreen implements InnerBaseGui{

    private JFrame frame;

    private static final double LANE_WIDTH_RATIO = 1.0 / 10.0;
    private static final double LANE_HEIGHT_RATIO = 1.0;
    private Lane[] lanes;
    
    public RhythmScreen(JFrame frame, Config config){
        
        this.frame = frame;

        lanes = new Lane[config.getLanes()];

        double laneWidth = this.frame.getContentPane().getSize().getWidth() * LANE_WIDTH_RATIO;
        double laneHeight = this.frame.getContentPane().getSize().getHeight() * LANE_HEIGHT_RATIO;

        //Create the lanes for the rhythm game
        for(int i = 0; i < config.getLanes(); i++){
            //Calculates lane positions in a method that centers the lanes
            int xPos = (int) (laneWidth / 2.0 + (laneWidth * (i - (config.getLanes() / 2.0))));

            //Add the width to center the lanes
            xPos += this.frame.getContentPane().getSize().getWidth() / 2;

            int yPos = (int) (laneHeight / 2.0);
            Lane lane = new Lane(xPos, yPos, this.frame, (int) laneWidth, (int) laneHeight);
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

    public Lane[] getLanes(){
        return this.lanes;
    }

    public JFrame getFrame()
    {
        return this.frame;
    }
}
