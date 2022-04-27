package main.notes;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import main.Lane;
import main.PaneConstants;

public class TapNote implements Note{

    private Lane lane;
    private int hitPoint;
    private JPanel notePanel;
    private double height;
    private double width;
    private double size;

    private static final double WIDTH_RATIO = 1.0 / 10.0;
    private static final double HEIGHT_RATIO = 1.0 / 20.0;

    public TapNote(JFrame frame, Lane lane, int hitPoint, double size)
    {
        this.lane = lane;
        this.hitPoint = hitPoint;
        this.notePanel = new JPanel();
        this.notePanel.setBackground(Color.CYAN);
        this.height = frame.getHeight() * HEIGHT_RATIO;
        this.width = frame.getWidth() * WIDTH_RATIO;
        this.size = size;

        frame.getLayeredPane().add(this.notePanel, PaneConstants.NOTES);
    }

    public TapNote(JFrame frame, Lane lane, int hitPoint, double size, int delay) {
        this.lane = lane;
        this.hitPoint = hitPoint + delay;
        this.notePanel = new JPanel();
        this.notePanel.setBackground(Color.CYAN);
        this.height = frame.getHeight() * HEIGHT_RATIO;
        this.width = frame.getWidth() * WIDTH_RATIO;
        this.size = size;

        frame.getLayeredPane().add(this.notePanel, PaneConstants.NOTES);
    }

    //If current point is 1000 and hitpoint is 1000 it's 0
    //If current point is 1500 and hitpoint is 1000 it's 500 (ahead)
    //If current point is 500 and hitpoint is 1000 it's -500 (behind)
    public double getPosition(long currentPoint)
    {
        return ((currentPoint - hitPoint) / size) + 1.0;
    }

    public void drawPosition(long currentPoint)
    {
        double position = getPosition(currentPoint);

        double y = this.lane.getHeight() * position;

        this.setPosition(this.lane.getXPos(), (int) y, (int) this.width, (int) this.height);
    }

    private void setPosition(int xCenter, int yCenter, int width, int height) {

        // xPos and yPos are the top left corner of the rectangle
        int xPos = xCenter - width / 2;
        int yPos = yCenter - height / 2; // Java starts from the top left so going up is lowering the y value

        this.notePanel.setBounds(xPos, yPos, width, height);
    }

    public int pointCalculation()
    {
        return 0;
    }
}
