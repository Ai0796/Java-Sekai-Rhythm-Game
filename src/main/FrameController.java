package main;

import main.gui.*;
import main.parser.Beatmap;
import main.settings.Config;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.WindowConstants;
public class FrameController {
    
    private StartScreen startScreen;
    private SongSelectionScreen songSelectionScreen;
    private Config config;
    private JFrame frame;

    public FrameController(Config config){

        this.config = config;

        frame = new JFrame("Stage");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(config.getX(), config.getY());

        startScreen = new StartScreen(frame, config, this);
        songSelectionScreen = new SongSelectionScreen(frame, config, this);

        startScreen.show();
    }

    public void startRhythmGame(){
        startScreen.hide();
        RhythmScreen rhythmScreen = new RhythmScreen(frame, config);

        this.setResizableFalse();
        rhythmScreen.show();

        frame.invalidate();
        frame.validate();
        frame.repaint();

        Beatmap beatmap = new Beatmap("src\\main\\temp_beatmaps\\Rachie - Thought Crime ([Aero]) [Izzel's Insane].osu");
        MusicPlayer music = new MusicPlayer("src\\main\\temp_beatmaps\\audio.mp3");


        Conductor conductor = new Conductor(rhythmScreen, beatmap, config);
        new Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.printf("Current Beat ms: %d%n", conductor.getPosition());
                conductor.incrementPosition();
                conductor.updateNotes();
                conductor.updateNotePosition();
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }
        }).start();

        music.play();
    }

    public void setResizableTrue() {
        this.frame.setResizable(true);
    }

    public void setResizableFalse() {
        this.frame.setResizable(false);
    }
}