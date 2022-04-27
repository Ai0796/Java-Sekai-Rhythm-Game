package main;

import java.util.logging.Level;
import java.util.ArrayList;

import main.gui.RhythmScreen;
import main.notes.TapNote;
import main.parser.Beatmap;
import main.parser.types.primitives.HitObject;
import main.parser.types.primitives.TimingPoint;
import main.settings.Config;

public class Conductor {

    private int delay;

    private long position;
    private long finalPosition;
    private long lastInstant;

    private double size;
    private double laneSize;
    private double bpm;

    private Lane[] lanes;

    private HitObject currentHitObject;
    private TimingPoint currentTimingPoint;
    private Beatmap beatmap;
    private Config config;
    private RhythmScreen rhythmScreen;

    private ArrayList<TapNote> notes;

    private static final int BUFFER_SIZE = 500;
    private static final double LANES_TOTAL_SIZE = 480;
    private static final double MAX_SIZE = 10000.0;

    public Conductor(RhythmScreen rhythmScreen, Beatmap beatmap, Config config)
    {
        this.lastInstant = System.currentTimeMillis();
        this.position = 0;
        this.rhythmScreen = rhythmScreen;
        this.beatmap = beatmap;
        this.config = config;
        this.size = MAX_SIZE / this.config.getSpeed();
        this.finalPosition = beatmap.osuHitObjects.getLength();
        this.lanes = rhythmScreen.getLanes();
        this.laneSize = LANES_TOTAL_SIZE / this.lanes.length;
        this.notes = new ArrayList<>();
        this.delay = config.getNoteDelay();

        this.currentHitObject = this.beatmap.osuHitObjects.getNextHitObject();
        this.currentTimingPoint = this.beatmap.osuTimingPoints.getNextTimingPoint();

        updateNotes();
    }

    public void incrementPosition(){
        long increment = System.currentTimeMillis() - this.lastInstant;
        this.lastInstant = System.currentTimeMillis();

        this.position += increment;
    }

    public long getPosition(){
        return this.position;
    }

    public void setPosition(long position)
    {
        this.position = position;
    }

    public double getbpm(){
        return this.bpm;
    }
    
    public long getFinalPosition(){
        return this.finalPosition;
    }
    
    public void updateNotes()
    {
        ArrayList<Integer> removeList = new ArrayList<>();
        double notePosition;

        //Removes all notes outside of the field
        for(int i = 0; i < notes.size(); i++){

            

            notePosition = this.notes.get(i).getPosition(this.position);
            if(notePosition > (1 + BUFFER_SIZE / size))
            {
                Main.logger.log(Level.INFO, String.format("Removed Note at time: %d", this.position));
                removeList.add(i);
            }
        }

        //Removes notes from back to front in order to prevent staggering
        for(int i = removeList.size() - 1; i >= 0; i--)
        {
            notes.remove(i);
        }

        //Adds notes that will then appear
        //Stores last hit object in case it didn't reach the time
        if(this.currentHitObject != null)
        {
            while(Long.valueOf(this.currentHitObject.time) < this.position + size)
            {
                int lane = (int) (this.currentHitObject.x / laneSize);
                
                //If lane is the exact edge then divide will leave it out of index
                if(lane == this.lanes.length)
                {
                    lane = this.lanes.length - 1;
                }
                TapNote note = new TapNote(this.rhythmScreen.getFrame(), this.lanes[lane], this.currentHitObject.time, this.size, this.delay);
                notes.add(note);

                Main.logger.log(Level.INFO, String.format("Added Note at time: %d", this.position));

                this.currentHitObject = this.beatmap.osuHitObjects.getNextHitObject();
                if(this.currentHitObject == null)
                {
                    break;
                }
            }
        }

        updateBPM();
    }

    private void updateBPM()
    {
        if(this.currentTimingPoint != null)
        {  
            while(this.currentTimingPoint.time < this.position)
            {
                this.currentTimingPoint = this.beatmap.osuTimingPoints.getNextTimingPoint();
                if(this.currentTimingPoint == null)
                {
                    break;
                }
            }
            if (this.currentTimingPoint != null)
            {
                //negative numbers inherit the previous bpm
                //Two if statements due to possible nullification
                if(this.currentTimingPoint.beatLength >= 0)
                {
                    this.bpm = 1.0 / this.currentTimingPoint.beatLength * 1000 * 60;
                }
            }
        }
    }

    public void updateNotePosition(){
        notes.forEach((note) -> note.drawPosition(this.position));
    }
}