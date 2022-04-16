package main;

import main.gui.RhythmScreen;
import main.notes.Note;
import main.notes.TapNote;
import main.settings.Config;

public class Conductor {

    private long position;
    private double bpm;
    private long finalPosition;
    private long lastInstant;

    private RhythmScreen rhythmScreen;

    TapNote[] notes;

    public Conductor(double bpm, long finalPosition, RhythmScreen rhythmScreen){
        this.position = 0;
        this.bpm = bpm;
        this.finalPosition = finalPosition;
        this.rhythmScreen = rhythmScreen;
        Config config = new Config();
        lastInstant = System.currentTimeMillis();

        Lane[] lane = rhythmScreen.getLanes();
        notes = new TapNote[1000];

        for(int i = 0; i < config.getLanes(); i++)
        {
            TapNote note = new TapNote(rhythmScreen.getFrame(), lane[i], i * 1000);

            System.out.println(note.getPosition(this.position)); 
            
            notes[i] = note;
        }
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
        for (TapNote tapNote : notes) {
            if(tapNote == null){
                break;
            }
            // System.out.println(tapNote.getPosition(this.position));
            tapNote.DrawPosition(this.position);
        }
    }
}