package main.parser;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageIconParser {

    private ImageIconParser(){}
    
    public static ImageIcon getImageIcon(String path) throws IOException
    {
        return new ImageIcon(ImageIO.read(new File(path)));
    }
}