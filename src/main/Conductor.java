package main;

import main.gui.RhythmScreen;

public class Conductor {

    private long position;
    private double bpm;
    private long finalPosition;
    private long lastInstant;

    private RhythmScreen rhythmScreen;

    public Conductor(double bpm, long finalPosition, RhythmScreen rhythmScreen){
        this.position = 0;
        this.bpm = bpm;
        this.finalPosition = finalPosition;
        this.rhythmScreen = rhythmScreen;
        lastInstant = System.currentTimeMillis();
    }

    public void incrementPosition(){
        long increment = System.currentTimeMillis() - this.lastInstant;
        this.lastInstant = System.currentTimeMillis();

        this.position += increment;
    }

    public long getPosition(){
        return this.position;
    }

    public double getbpm(){
        return this.bpm;
    }
    
    public long getFinalPosition(){
        return this.finalPosition;
    }

    public void updateNotePosition(){
        RhythmScreen.updateNotePosition(this.position);
    }
}
