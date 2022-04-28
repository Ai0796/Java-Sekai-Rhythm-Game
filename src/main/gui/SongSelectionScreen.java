package main.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import main.FrameController;
import main.Main;
import main.MusicPlayer;
import main.PaneConstants;
import main.parser.Beatmap;
import main.parser.ImageIconParser;
import main.parser.types.BeatmapDic;
import main.parser.types.primitives.Event;
import main.parser.types.primitives.Background;
import main.settings.Config;

public class SongSelectionScreen implements InnerBaseGui
{

    private JFrame frame;
    private FrameController frameController;
    private Config config;
    private int difficultySelect;
    private int songSelect;
    private HashMap<String, ArrayList<Beatmap>> beatmapDic;
    private String[] beatmapKeys;
    private JLabel background;
    private MusicPlayer music;

    //TODO
    //Temp showcase code
    JLabel titleLabel;
    JLabel difficultyLabel;

    private static final int FONT_STYLE = 1;

    double fontRatio = 1.0 / 2.0;

    double titleLabelx = 1.0 / 2.0;
    double titleLabely = 1.0 / 2.0;
    double titleLabelWidth = 1.0 / 2.0;
    double titleLabelHeight = 1.0 / 6.0;

    double difficultyLabelx = 1.0 / 2.0;
    double difficultyLabely = 5.0 / 6.0;

    double difficultyLabelWidth = 1.0 / 3.0;
    double difficultyLabelHeight = 1.0 / 12.0;

    public SongSelectionScreen(JFrame frame, Config config, FrameController frameController, BeatmapDic beatmapDic)
    {
        this.config = config;
        this.frame = frame;
        this.frameController = frameController;
        this.songSelect = 0;
        this.difficultySelect = 0;
        this.beatmapDic = beatmapDic.getBeatmapDic();
        this.beatmapKeys = this.beatmapDic.keySet().toArray(new String[this.beatmapDic.keySet().size()]);
        this.music = null;
        
        this.background = new JLabel();
        
        this.titleLabel = new JLabel();
        this.difficultyLabel = new JLabel();

        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleLabel.setBackground(Color.gray);
        this.difficultyLabel.setBackground(Color.LIGHT_GRAY);

        this.titleLabel.setOpaque(true);
        this.difficultyLabel.setOpaque(true);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                int x = (int) componentEvent.getComponent().getSize().getWidth();
                int y = (int) componentEvent.getComponent().getSize().getHeight();
                resetObjectPosition(x, y);
            }
        });
    }

    //TODO
    //Remove ugly variable names, make it dynamic
    private void resetObjectPosition(int x, int y)
    {
        int titleLabelx = (int) (x * this.titleLabelx);
        int titleLabely = (int) (y * this.titleLabely);
        int titleLabelWidth = (int) (x * this.titleLabelWidth);
        int titleLabelHeight = (int) (y * this.titleLabelHeight);

        int difficultyLabelx = (int) (x * this.difficultyLabelx);
        int difficultyLabely = (int) (y * this.difficultyLabely);
        int difficultyLabelWidth = (int) (x * this.difficultyLabelWidth);
        int difficultyLabelHeight = (int) (y * this.difficultyLabelHeight);

        Font titleFont = new Font(this.config.getButtonFont(), FONT_STYLE,
                (int) (fontRatio * titleLabelHeight));

        Font difficultyFont = new Font(this.config.getButtonFont(), FONT_STYLE,
                (int) (fontRatio * difficultyLabelHeight));

        this.titleLabel.setFont(titleFont);
        this.difficultyLabel.setFont(difficultyFont);

        setPosition(this.titleLabel, titleLabelx, titleLabely, titleLabelWidth, titleLabelHeight);
        setPosition(this.difficultyLabel, difficultyLabelx, difficultyLabely, difficultyLabelWidth, difficultyLabelHeight);
    }

    private void update()
    {
        frame.invalidate();
        frame.validate();
        frame.repaint();
        resetObjectPosition(frame.getWidth(), frame.getHeight());
    }

    private void playCurrentSong()
    {
        this.hide();
        Beatmap beatmap = beatmapDic.get(beatmapKeys[this.songSelect]).get(this.difficultySelect);
        this.frameController.startRhythmGame(beatmap);
    }

    public void show() {
        addListeners();
        this.frame.getLayeredPane().add(this.titleLabel, PaneConstants.FOREGROUND);
        this.frame.getLayeredPane().add(this.difficultyLabel, PaneConstants.FOREGROUND);
        this.frame.getLayeredPane().add(this.background, PaneConstants.BACKGROUND);
        this.frame.setVisible(true);

        //Settings default values
        this.songSelect = 0;
        this.difficultySelect = 0;
        updateSong();
        updateDifficulty();
        resetObjectPosition(frame.getWidth(), frame.getHeight());
        update();
    }

    public void hide() 
    {
        if (music != null) {
            music.close();
        }
        frame.getLayeredPane().removeAll();
    }

    private void updateDifficulty()
    {
        ArrayList<Beatmap> beatmapList = beatmapDic.get(beatmapKeys[this.songSelect]);
        if (this.difficultySelect >= beatmapList.size() || this.difficultySelect < 0) 
        {
            this.difficultySelect %= beatmapList.size();
            while (this.difficultySelect < 0) 
            {
                this.difficultySelect += beatmapList.size();
            }
        }

        Beatmap beatmap = beatmapList.get(this.difficultySelect);

        String fString = String.format("%.2f: %s", beatmap.osuDifficulty.OverallDifficulty, beatmap.osuMetadata.Version);
        this.difficultyLabel.setText(fString);

        this.update();
    }

    private void updateSong()
    {
        if(this.songSelect >= this.beatmapDic.size() || this.songSelect < 0)
        {
            this.songSelect %= this.beatmapDic.size();
            while(this.songSelect < 0)
            {
                this.songSelect += this.beatmapDic.size();
            }
        }

        //Default back to 0
        this.difficultySelect = 0;

        Beatmap beatmap = beatmapDic.get(beatmapKeys[this.songSelect]).get(this.difficultySelect);
        this.titleLabel.setText(beatmap.osuMetadata.Title);

        this.updateDifficulty(); //Update difficulty text
        this.update();
        this.updateBackgroundImage(beatmap);
        this.resetMusicPlayer(beatmap.Folder + "\\" + beatmap.osuGeneral.AudioFilename);
    }

    private void resetMusicPlayer(String path)
    {
        if(music != null)
        {
            music.close();
        }

        music = new MusicPlayer(path);
        music.play();
    }

    private void setPosition(JLabel label,int xCenter, int yCenter, int width, int height) {

        // xPos and yPos are the top left corner of the rectangle
        int xPos = xCenter - width / 2;
        int yPos = yCenter - height / 2; // Java starts from the top left so going up is lowering the y value

        label.setBounds(xPos, yPos, width, height);
    }

    private void addListeners()
    {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                switch(keyCode)
                {
                    case PaneConstants.KEY_LEFT:
                        difficultySelect -= 1;
                        updateDifficulty();
                        break;
                    case PaneConstants.KEY_RIGHT:
                        difficultySelect += 1;
                        updateDifficulty();
                        break;
                    case PaneConstants.KEY_DOWN:
                        songSelect -= 1;
                        updateSong();
                        break;
                    case PaneConstants.KEY_UP:
                        songSelect += 1;
                        updateSong();
                        break;
                    case PaneConstants.KEY_ENTER:
                        playCurrentSong();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

    // Scales to fit height
    private ImageIcon scaleImage(int maxHeight, ImageIcon originalImage) {
        int newWidth = (originalImage.getIconWidth() * maxHeight) / originalImage.getIconHeight();

        Image image = originalImage.getImage();
        return new ImageIcon(image.getScaledInstance(newWidth, maxHeight, java.awt.Image.SCALE_SMOOTH));
    }

    private void setCenteredLabelImage(String path, JLabel label, int x , int y, int width, int heigth) {
        try {
            ImageIcon image = scaleImage(frame.getHeight(), ImageIconParser.getImageIcon(path));
            int xPos = x - (image.getIconWidth() / 2);
            int yPos = y - (image.getIconHeight() / 2);

            label.setIcon(image);
            label.setBounds(xPos, yPos, image.getIconWidth(), image.getIconHeight());

            Main.logger.log(Level.INFO, "Image loaded");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            Main.logger.log(Level.WARNING, "Image to load");
            e1.printStackTrace();
        }
    }

    private void updateBackgroundImage(Beatmap beatmap) {

        Background event = null;

        event = beatmap.osuEvents.getFirstBackground();

        if (event != null) {
            String path = beatmap.Folder + "\\" + event.filename;
            setBackgroundImage(path);
        }
    }

    private void setBackgroundImage(String path) {
        try {
            ImageIcon backgroundImage = scaleImage(frame.getHeight(), ImageIconParser.getImageIcon(path));
            int xPos = (frame.getWidth() / 2) - (backgroundImage.getIconWidth() / 2);

            background.setIcon(backgroundImage);
            background.setBounds(xPos, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

            Main.logger.log(Level.INFO, "Background loaded");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            Main.logger.log(Level.WARNING, "Background failed to load");
            e1.printStackTrace();
        }
    }
}