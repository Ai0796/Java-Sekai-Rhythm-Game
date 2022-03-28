package main.gui;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.Font;

import main.settings.Config;

public class StartScreen implements InnerBaseGui{

    private JButton startButton;

    private Config config;
    private JFrame frame;

    static private final int FONT_STYLE = 1;
    static private final double FONT_RATIO = 1.0 / 2.0; // Used to determine max font for the buttons
    static private final double BUTTON_HEIGHT_RATIO = 1.0 / 6.0;
    static private final double BUTTON_WIDTH_RATIO = 3.0 / 4.0;

    

    public StartScreen(JFrame frame, Config config, JButton startButton){

        this.config = config;
        this.frame = frame;
        this.startButton = startButton;

        frame.getContentPane().setBackground(Color.DARK_GRAY);
        
        // startButton = new JButton("Press To Start");

        startButton.setFocusPainted(false);
        startButton.setBackground(Color.CYAN);
        startButton.setForeground(Color.BLACK);

        resetObjectPosition(this.config.getX(), this.config.getY());

        frame.getContentPane().add(startButton);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int x = (int) componentEvent.getComponent().getSize().getWidth();
                int y = (int) componentEvent.getComponent().getSize().getHeight();
                resetObjectPosition(x, y);
            }
        });
    }


    private void resetObjectPosition(int x, int y){

        Font buttonFont = new Font(this.config.getButtonFont(), FONT_STYLE, (int)(FONT_RATIO * BUTTON_HEIGHT_RATIO * y));

        System.out.println(BUTTON_WIDTH_RATIO);

        setButtonAbsolutePosition(startButton, x / 2, y / 2, (int)(x * BUTTON_WIDTH_RATIO), (int)(y * BUTTON_HEIGHT_RATIO));
        startButton.setFont(buttonFont);
    }
    
    //Given x and y centers and width and height puts a rectangle with the center being on xCenter, yCenter
    private void setButtonAbsolutePosition(JButton button, int xCenter, int yCenter, int width, int height) {

        System.out.printf("%d, %d, %d, %d\n", xCenter, yCenter, width, height);

        //xPos and yPos are the top left corner of the rectangle
        int xPos = xCenter - width / 2;
        int yPos = yCenter - height / 2; //Java starts from the top left so going up is lowering the y value

        // System.out.printf("%d, %d, %d, %d\n", left, right, top, bottom);

        button.setBounds(xPos, yPos, width, height);
    }

    public void show(){
        frame.getContentPane().removeAll();

        frame.getContentPane().add(startButton);

        this.frame.setVisible(true);
    }

    public void hide(){

        frame.getContentPane().removeAll();

        // this.frame.setVisible(false);
    }

    public void setResizableTrue(){
        this.frame.setResizable(true);
    }

    public void setResizableFalse(){
        this.frame.setResizable(false);
    }
}
