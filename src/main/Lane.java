package main;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lane {
    
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private JPanel lane;

    public Lane(int xPos, int yPos, JFrame frame, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.lane = new JPanel();
        this.lane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.lane.setOpaque(false);

        frame.add(this.lane);

        setPanelAbsolutePosition(this.lane, this.xPos, this.yPos, this.width, this.height);
    }

    public void increment(int interval){

    }

    private void setPanelAbsolutePosition(JPanel panel, int xCenter, int yCenter, int width, int height) {

        // System.out.printf("%d, %d, %d, %d\n", xCenter, yCenter, width, height);

        // xPos and yPos are the top left corner of the rectangle
        int xPos = xCenter - width / 2;
        int yPos = yCenter - height / 2; // Java starts from the top left so going up is lowering the y value

        // System.out.printf("%d, %d, %d, %d\n", left, right, top, bottom);

        panel.setBounds(xPos, yPos, width, height);
    }

    public int getXPos()
    {
        return xPos;
    }

    public int getYPos()
    {
        return yPos;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
