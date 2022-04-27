package main.gui;

import javax.swing.JFrame;

import main.FrameController;
import main.parser.Beatmap;
import main.settings.Config;

public class SongSelectionScreen implements InnerBaseGui
{

    private JFrame frame;
    private FrameController frameController;
    private Config config;
    private int difficulty;
    private int song;
    private int beatmapAmount;

    public SongSelectionScreen(JFrame frame, Config config, FrameController frameController, Beatmap[] beatmaps)
    {
        this.config = config;
        this.frame = frame;
        this.frameController = frameController;
        this.song = 0;
        this.difficulty = 0;
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public void hide() {
        this.frame.setVisible(false);
    }

    private void updateDifficulty()
    {

    }

    private void updateSong()
    {

    }
}