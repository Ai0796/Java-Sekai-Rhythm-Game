package main.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.parser.types.OsuColours;
import main.parser.types.OsuDifficulty;
import main.parser.types.OsuEditor;
import main.parser.types.OsuEvents;
import main.parser.types.OsuGeneral;
import main.parser.types.OsuHitObjects;
import main.parser.types.OsuMetadata;
import main.parser.types.OsuTimingPoints;
import main.parser.types.Parser;

public class BeatmapParser {

    private OsuGeneral osuGeneral;
    private OsuEditor osuEditor;
    private OsuMetadata osuMetadata;
    private OsuDifficulty osuDifficulty;
    private OsuEvents osuEvents;
    private OsuTimingPoints osuTimingPoints;
    private OsuColours osuColours;
    private OsuHitObjects osuHitObjects;
    
    public BeatmapParser(String path)
    {
        BufferedReader reader;
        String line;
        Parser currentObject;

        try {
            reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();
            while(line != null){
                //Do something
                //TODO read lines
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
