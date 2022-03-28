package main;

import main.gui.*;
import main.settings.Config;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameController {
    
    private static StartScreen startScreen;
    private static RhythmScreen rhythmScreen;
    private Config config;
    private JFrame frame;

    public FrameController(Config config){

        this.config = config;

        frame = new JFrame("Stage");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(config.getX(), config.getY());

        JButton startButton = new JButton("Press To Start");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startRhythmGame();
            }
        });

        startScreen = new StartScreen(frame, config, startButton);
        rhythmScreen = new RhythmScreen(frame, config);

        startScreen.show();
    }

    private void startRhythmGame(){
        startScreen.hide();
        rhythmScreen.show();

        rhythmScreen.startGame();

        frame.invalidate();
        frame.validate();
        frame.repaint();
    }
}
