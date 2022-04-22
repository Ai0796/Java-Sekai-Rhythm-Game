package main;

import javax.swing.JLayeredPane;

public class PaneConstants 
{

    private PaneConstants(){} //Hides the default public constructor

    public static final Integer BACKGROUND = JLayeredPane.DEFAULT_LAYER;
    public static final Integer FOREGROUND = JLayeredPane.PALETTE_LAYER;
    public static final Integer NOTES = JLayeredPane.MODAL_LAYER;
}
