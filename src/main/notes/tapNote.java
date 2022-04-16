package main.notes;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import main.Lane;

public class TapNote implements Note{
    private Lane lane;
    private int hitPoint;
    private JPanel notePanel;
    private double height;
    private double width;

    private static final double widthRatio = 1.0 / 10.0;
    private static final double heightRatio = 1.0 / 20.0;

    public TapNote(JFrame frame, Lane lane, int hitPoint)
    {
        this.lane = lane;
        this.hitPoint = hitPoint;
        this.notePanel = new JPanel();
        this.notePanel.setBackground(Color.CYAN);
        this.height = frame.getHeight() * heightRatio;
        this.width = frame.getWidth() * widthRatio;

        frame.getContentPane().add(this.notePanel);
    }

    public double getPosition(long currentPoint)
    {
        return (currentPoint - hitPoint) / 5000.0;
    }

    public void DrawPosition(long currentPoint)
    {
        double position = getPosition(currentPoint);

        double y = this.lane.getHeight() * position;
        double height = this.lane.getHeight() * heightRatio;
        double width = this.lane.getWidth() * widthRatio;

        this.setPosition(this.lane.getXPos(), (int) y, (int) this.width, (int) this.height);
    }

    private void setPosition(int xCenter, int yCenter, int width, int height) {

        // xPos and yPos are the top left corner of the rectangle
        int xPos = xCenter - width / 2;
        int yPos = yCenter - height / 2; // Java starts from the top left so going up is lowering the y value

        this.notePanel.setBounds(xPos, yPos, width, height);
    }
}
