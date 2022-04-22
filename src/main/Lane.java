package main;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lane {
    
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private JPanel lanePanel;

    public Lane(int xPos, int yPos, JFrame frame, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.lanePanel = new JPanel();
        this.lanePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.lanePanel.setOpaque(false);

        frame.getLayeredPane().add(this.lanePanel, PaneConstants.FOREGROUND);

        setPanelAbsolutePosition(this.lanePanel, this.xPos, this.yPos, this.width, this.height);
    }

    private void setPanelAbsolutePosition(JPanel panel, int xCenter, int yCenter, int width, int height) 
    {
        // xPos and yPos are the top left corner of the rectangle
        int x = xCenter - width / 2;
        int y = yCenter - height / 2; // Java starts from the top left so going up is lowering the y value

        panel.setBounds(x, y, width, height);
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
