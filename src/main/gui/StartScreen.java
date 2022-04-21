package main.gui;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.Font;

import main.FrameController;
import main.settings.Config;

public class StartScreen implements InnerBaseGui{

    private JButton startButton;

    private Config config;
    private JFrame frame;
    private FrameController frameController;

    static private final int FONT_STYLE = 1;
    static private final double FONT_RATIO = 1.0 / 2.0; // Used to determine max font for the buttons
    static private final double BUTTON_HEIGHT_RATIO = 1.0 / 6.0;
    static private final double BUTTON_WIDTH_RATIO = 3.0 / 4.0;

    public StartScreen(JFrame frame, Config config, FrameController frameController){

        this.config = config;
        this.frame = frame;
        this.frameController = frameController;

        frame.getContentPane().setBackground(Color.DARK_GRAY);
        
        startButton = new JButton("Press To Start");

        frame.getContentPane().add(startButton);

        //Resizes on windows size change
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int x = (int) componentEvent.getComponent().getSize().getWidth();
                int y = (int) componentEvent.getComponent().getSize().getHeight();
                resetObjectPosition(x, y);
            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                frameController.startRhythmGame();
            }
        });

        // startButton.addActionListener(new );
    }


    private void resetObjectPosition(int x, int y){

        Font buttonFont = new Font(this.config.getButtonFont(), FONT_STYLE, (int)(FONT_RATIO * BUTTON_HEIGHT_RATIO * y));

        setButtonAbsolutePosition(startButton, x / 2, y / 2, (int)(x * BUTTON_WIDTH_RATIO), (int)(y * BUTTON_HEIGHT_RATIO));
        startButton.setFont(buttonFont);
    }
    
    //Given x and y centers and width and height puts a rectangle with the center being on xCenter, yCenter
    private void setButtonAbsolutePosition(JButton button, int xCenter, int yCenter, int width, int height) {

        //xPos and yPos are the top left corner of the rectangle
        int xPos = xCenter - width / 2;
        int yPos = yCenter - height / 2; //Java starts from the top left so going up is lowering the y value

        button.setBounds(xPos, yPos, width, height);
    }

    public void show(){
        frame.getContentPane().removeAll();

        frame.getContentPane().add(startButton);

        startButton.setFocusPainted(false);
        startButton.setBackground(Color.CYAN);
        startButton.setForeground(Color.BLACK);

        resetObjectPosition(this.config.getX(), this.config.getY());

        this.frame.setVisible(true);
    }

    public void hide(){

        frame.getContentPane().removeAll();

        // this.frame.setVisible(false);
    }
}
