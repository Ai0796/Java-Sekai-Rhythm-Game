package main;

import main.gui.*;
import main.settings.Config;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class FrameController {
    
    private StartScreen startScreen;
    private RhythmScreen rhythmScreen;
    private SongSelectionScreen songSelectionScreen;
    private Config config;
    private JFrame frame;

    public FrameController(Config config){

        this.config = config;

        frame = new JFrame("Stage");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(config.getX(), config.getY());

        JButton startButton = new JButton("Press To Start");

        startScreen = new StartScreen(frame, config, this);
        songSelectionScreen = new SongSelectionScreen(frame, config, this);

        startScreen.show();
    }

    public void startRhythmGame(){
        startScreen.hide();
        rhythmScreen = new RhythmScreen(frame, config, this);

        this.setResizableFalse();
        rhythmScreen.show();

        // rhythmScreen.startGame();
        frame.invalidate();
        frame.validate();
        frame.repaint();
        
        Conductor conductor = new Conductor(200, 10000000, rhythmScreen);

        // while(conductor.getPosition() < conductor.getFinalPosition()){
        new Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println(conductor.getPosition());
                conductor.incrementPosition();
                conductor.updateNotePosition();
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }
        }).start();
    }

    public void setResizableTrue() {
        this.frame.setResizable(true);
    }

    public void setResizableFalse() {
        this.frame.setResizable(false);
    }
}
