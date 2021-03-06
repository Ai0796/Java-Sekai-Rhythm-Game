package main;

import main.gui.*;
import main.parser.Beatmap;
import main.parser.ImageIconParser;
import main.parser.ParseBeatmapPaths;
import main.parser.types.BeatmapDic;
import main.parser.types.primitives.Background;
import main.settings.Config;

import javax.swing.ImageIcon;

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
    private BeatmapDic beatmapDic;

    public FrameController(Config config){

        this.config = config;
        this.beatmapDic = ParseBeatmapPaths.parse();

        frame = new JFrame("Stage");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setLayout(null);
        frame.setSize(config.getX(), config.getY());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        frame.setContentPane(layeredPane);

        startScreen = new StartScreen(frame, config, this);
        songSelectionScreen = new SongSelectionScreen(frame, config, this, beatmapDic);

        startScreen.show();
    }

    public void startSongSelection()
    {
        startScreen.hide();
        songSelectionScreen.show();
    }

    public void startRhythmGame(Beatmap beatmap){
        startScreen.hide();
        songSelectionScreen.hide();
        RhythmScreen rhythmScreen = new RhythmScreen(frame, config);

        this.setResizableFalse();

        frame.invalidate();
        frame.validate();
        frame.repaint();

        String beatMapFolder = beatmap.Folder;
        String beatMapMusicPath = beatMapFolder + "/" + beatmap.osuGeneral.AudioFilename;
        
        MusicPlayer music = new MusicPlayer(beatMapMusicPath);
        updateBackgroundImage(beatmap);

        music.play();
        Conductor conductor = new Conductor(rhythmScreen, beatmap, config);

        Main.logger.log(Level.INFO, String.format("Conductor loaded at: %d", System.currentTimeMillis()));
        new Timer(1000 / config.getFrameRate(), new ActionListener() {
            public void actionPerformed(ActionEvent e){
                conductor.update();
                if(conductor.getFinalPosition() + config.getEndScreenTime() < conductor.getPosition() && music.isComplete())
                {
                    ((Timer) e.getSource()).stop();
                    setResizableTrue();
                    startSongSelection();
                }
                repaint();
            }
        }).start();
    }

    private void repaint()
    {
        frame.invalidate();
        frame.validate();
        frame.repaint();
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

    public void updateBackgroundImage(Beatmap beatmap)
    {
        Background event = null;

        event = beatmap.osuEvents.getFirstBackground();
        
        if(event != null)
        {
            String path = beatmap.Folder + "\\" + event.filename;
            setBackgroundImage(path);
        }
    }

    public void setBackgroundImage(String path)
    {
        try 
        {
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