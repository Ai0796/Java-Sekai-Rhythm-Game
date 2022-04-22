package main;

import main.gui.*;
import main.gui.JPictureBox.SizeMode;
import main.parser.Beatmap;
import main.parser.ImageIconParser;
import main.settings.Config;

import javax.swing.ImageIcon;
import java.awt.Component;
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

    public FrameController(Config config){

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

        Beatmap beatmap = new Beatmap("src\\main\\temp_beatmaps\\Rachie - Thought Crime ([Aero]) [Izzel's Insane].osu");
        MusicPlayer music = new MusicPlayer("src\\main\\temp_beatmaps\\audio.mp3");

        try {
            JLabel background = new JLabel();
            ImageIcon backgroundImage = scaleImage(frame.getHeight(), ImageIconParser.getImageIcon("src\\main\\temp_beatmaps\\66041517_p0.png"));
            int xPos = (frame.getWidth() / 2) - (backgroundImage.getIconWidth() / 2);
            
            background.setIcon(backgroundImage);
            background.setBounds(xPos, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
            frame.getLayeredPane().add(background, PaneConstants.BACKGROUND);

            System.out.println("Background loaded");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            System.out.println("Background failed to load");
            e1.printStackTrace();
        }

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

    //Scales to fit height
    private ImageIcon scaleImage(int maxHeight, ImageIcon originalImage)
    {
        int newWidth = (originalImage.getIconWidth() * maxHeight) / originalImage.getIconHeight();

        Image image = originalImage.getImage();
        return new ImageIcon(image.getScaledInstance(newWidth, maxHeight, java.awt.Image.SCALE_SMOOTH));
    }
}