package main;

import javax.swing.JLayeredPane;

public class PaneConstants 
{

    private PaneConstants(){} //Hides the default public constructor

    public static final Integer BACKGROUND = JLayeredPane.DEFAULT_LAYER;
    public static final Integer FOREGROUND = JLayeredPane.PALETTE_LAYER;
    public static final Integer NOTES = JLayeredPane.MODAL_LAYER;
    public static final int KEY_LEFT = 37;
    public static final int KEY_UP = 38;
    public static final int KEY_RIGHT = 39;
    public static final int KEY_DOWN = 40;
    public static final int KEY_ENTER = 10;
}
