package main;

import main.gui.*;
import main.parser.Beatmap;
import main.parser.ImageIconParser;
import main.settings.Config;

import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.WindowConstants;
public class FrameController {
    
    private StartScreen startScreen;
    private SongSelectionScreen songSelectionScreen;
    private Config config;
    private JFrame frame;
    private ArrayList<String> beatmapFolders;

    public FrameController(Config config, ExtractBeatmaps beatmaps){

        this.config = config;

        frame = new JFrame("Stage");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(config.getX(), config.getY());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        frame.setContentPane(layeredPane);

        startScreen = new StartScreen(frame, config, this);
        songSelectionScreen = new SongSelectionScreen(frame, config, this);

        startScreen.show();
    }

    public void startRhythmGame(){
        startScreen.hide();
        RhythmScreen rhythmScreen = new RhythmScreen(frame, config);

        this.setResizableFalse();

        frame.invalidate();
        frame.validate();
        frame.repaint();

        Beatmap beatmap = new Beatmap("src\\main\\temp_beatmaps\\257607 xi - FREEDOM DiVE.osz\\xi - FREEDOM DiVE (elchxyrlia) [Arles].osu");
        MusicPlayer music = new MusicPlayer("src\\main\\temp_beatmaps\\257607 xi - FREEDOM DiVE.osz\\12 FREEDOM DiVE.mp3");

        setBackgroundImage("src\\main\\temp_beatmaps\\257607 xi - FREEDOM DiVE.osz\\dive.png");

        music.play();
        Conductor conductor = new Conductor(rhythmScreen, beatmap, config);

        Main.logger.log(Level.INFO, String.format("Conductor loaded at: %d", System.currentTimeMillis()));
        new Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Main.logger.log(Level.INFO, String.format("Current Beat ms: %d%n", conductor.getPosition()));
                conductor.incrementPosition();
                conductor.updateNotes();
                conductor.updateNotePosition();
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }
        }).start();
    }

    public void setResizableTrue() {
        this.frame.setResizable(true);
    }

    public void setResizableFalse() {
        this.frame.setResizable(false);
    }

    //Scales to fit height
    private ImageIcon scaleImage(int maxHeight, ImageIcon originalImage)
    {
        int newWidth = (originalImage.getIconWidth() * maxHeight) / originalImage.getIconHeight();

        Image image = originalImage.getImage();
        return new ImageIcon(image.getScaledInstance(newWidth, maxHeight, java.awt.Image.SCALE_SMOOTH));
    }

    private void setBackgroundImage(String path)
    {
         try {
            JLabel background = new JLabel();
            ImageIcon backgroundImage = scaleImage(frame.getHeight(), ImageIconParser.getImageIcon(path));
            int xPos = (frame.getWidth() / 2) - (backgroundImage.getIconWidth() / 2);

            background.setIcon(backgroundImage);
            background.setBounds(xPos, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
            frame.getLayeredPane().add(background, PaneConstants.BACKGROUND);

            Main.logger.log(Level.INFO, "Background loaded");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            Main.logger.log(Level.WARNING, "Background failed to load");
            e1.printStackTrace();
        }
    }
}