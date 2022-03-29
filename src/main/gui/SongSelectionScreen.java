package main.gui;

import javax.swing.JFrame;

import main.FrameController;
import main.settings.Config;

public class SongSelectionScreen implements InnerBaseGui
{

    private JFrame frame;
    private FrameController frameController;
    private Config config;

    public SongSelectionScreen(JFrame frame, Config config, FrameController frameController)
    {
        this.config = config;
        this.frame = frame;
        this.frameController = frameController;
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public void hide() {
        this.frame.setVisible(false);
    }
}