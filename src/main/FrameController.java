package main;

import main.gui.*;
import main.parser.Beatmap;
import main.settings.Config;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class FrameController {
    
    private StartScreen startScreen;
    private RhythmScreen rhythmScreen;
    private SongSelectionScreen songSelectionScreen;
    private Config config;
    private JFrame frame;

    public FrameController(Config config){

        this.config = config;

        frame = new JFrame("Stage");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(config.getX(), config.getY());

        JButton startButton = new JButton("Press To Start");

        startScreen = new StartScreen(frame, config, this);
        songSelectionScreen = new SongSelectionScreen(frame, config, this);

        startScreen.show();
    }

    public void startRhythmGame(){
        startScreen.hide();
        rhythmScreen = new RhythmScreen(frame, config, this);

        this.setResizableFalse();
        rhythmScreen.show();

        // rhythmScreen.startGame();
        frame.invalidate();
        frame.validate();
        frame.repaint();

        Beatmap beatmap = new Beatmap("src\\main\\temp_beatmaps\\Rachie - Thought Crime ([Aero]) [Izzel's Insane].osu");
        MusicPlayer music = new MusicPlayer("src\\main\\temp_beatmaps\\audio.mp3");

        try {
            frame.getRootPane().add(new JLabel(new ImageIcon(ImageIO.read(new File("src\\main\\temp_beatmaps\\66041517_p0.png")))));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Conductor conductor = new Conductor(rhythmScreen, beatmap, config);
        // while(conductor.getPosition() < conductor.getFinalPosition()){
        new Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println(conductor.getPosition());
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
