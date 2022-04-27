package main.settings;

import java.util.Properties;

public class Config {
    
    Properties properties;
    private int x_size;
    private int y_size;
    private double speed;
    private String buttonFont;
    private int lanes;
    private int endScreenTime;
    private int noteDelay;
    private int frameRate;

    public Config(){
        properties = ReadPropertyFile.readFile();

        x_size = Integer.parseInt(properties.getProperty("x_size"));
        y_size = Integer.parseInt(properties.getProperty("y_size"));
        speed = Double.parseDouble(properties.getProperty("speed"));
        buttonFont = properties.getProperty("Font");
        lanes = Integer.parseInt(properties.getProperty("lanes"));
        endScreenTime = Integer.parseInt(properties.getProperty("endScreenTime"));
        noteDelay = Integer.parseInt(properties.getProperty("noteDelay"));
        frameRate = Integer.parseInt(properties.getProperty("frameRate"));
    }

    public int getX() {
        return x_size;
    }

    public int getY() {
        return y_size;
    }

    public double getSpeed() {
        return speed;
    }

    public String getButtonFont() {
        return buttonFont;
    }

    public int getLanes() {
        return lanes;
    }

    public int getEndScreenTime() {
        return endScreenTime;
    }

    public int getNoteDelay() {
        return noteDelay;
    }

    public int getFrameRate() {
        return frameRate;
    }
}
