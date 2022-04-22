package main.gui;

import javax.swing.JFrame;

import main.Lane;
import main.settings.Config;

public class RhythmScreen implements InnerBaseGui{

    private JFrame frame;

    private static final double LANE_WIDTH_RATIO = 1.0 / 10.0;
    private static final double LANE_HEIGHT_RATIO = 1.25; //Make it slightly bigger than the screen to not see the top/bot borders
    private Lane[] lanes;
    
    public RhythmScreen(JFrame frame, Config config){
        
        this.frame = frame;

        lanes = new Lane[config.getLanes()];

        double laneWidth = this.frame.getLayeredPane().getSize().getWidth() * LANE_WIDTH_RATIO;
        double laneHeight = this.frame.getLayeredPane().getSize().getHeight() * LANE_HEIGHT_RATIO;

        double startLocation = (laneWidth / 2.0 + (laneWidth * (config.getLanes() / -2.0)));
        int xPos, yPos;

        //Create the lanes for the rhythm game
        for(int i = 0; i < config.getLanes(); i++){
            //Calculates lane positions in a method that centers the lanes

            //Add half the width to center the lanes
            xPos = (int) Math.round(startLocation + this.frame.getLayeredPane().getSize().getWidth() / 2);
            yPos = (int) (this.frame.getLayeredPane().getSize().getHeight() / 2.0);

            Lane lane = new Lane(xPos, yPos, this.frame, (int) laneWidth, (int) laneHeight);
            lanes[i] = lane;

            startLocation += laneWidth;
        }
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
