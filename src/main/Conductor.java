package main;

public class Conductor {

    private long position;
    private double bpm;
    private long finalPosition;
    private long lastInstant;

    public Conductor(double bpm, long finalPosition){
        this.position = 0;
        this.bpm = bpm;
        this.finalPosition = finalPosition;
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
}
