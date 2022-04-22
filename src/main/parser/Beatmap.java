package main.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

import main.Main;
import main.parser.types.OsuColours;
import main.parser.types.OsuDifficulty;
import main.parser.types.OsuEditor;
import main.parser.types.OsuEvents;
import main.parser.types.OsuGeneral;
import main.parser.types.OsuHitObjects;
import main.parser.types.OsuMetadata;
import main.parser.types.OsuTimingPoints;
import main.parser.types.Parser;

public class Beatmap {

    //TODO make accessors
    public OsuGeneral osuGeneral;
    public OsuEditor osuEditor;
    public OsuMetadata osuMetadata;
    public OsuDifficulty osuDifficulty;
    public OsuEvents osuEvents;
    public OsuTimingPoints osuTimingPoints;
    public OsuColours osuColours;
    public OsuHitObjects osuHitObjects;

    public Beatmap()
    {
        osuGeneral = new OsuGeneral();
        osuEditor = new OsuEditor();
        osuMetadata = new OsuMetadata();
        osuDifficulty = new OsuDifficulty();
        osuEvents = new OsuEvents();
        osuColours = new OsuColours();
        osuHitObjects = new OsuHitObjects();
    }

    public Beatmap(String path) {
        osuGeneral = new OsuGeneral();
        osuEditor = new OsuEditor();
        osuMetadata = new OsuMetadata();
        osuDifficulty = new OsuDifficulty();
        osuEvents = new OsuEvents();
        osuColours = new OsuColours();
        osuTimingPoints = new OsuTimingPoints();
        osuHitObjects = new OsuHitObjects();

        this.parse(path);
    }
    
    public void parse(String path)
    {
        BufferedReader reader;
        String line;
        Parser currentObject = null;

        try {
            reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();
            while(line != null){

                line = line.strip(); //Strips whitespace

                //Checks if line is a whitespace
                if(line.length() == 0)
                {
                    line = reader.readLine();
                    continue;
                }

                //Checks if the line is a comment
                else if (line.charAt(0) == '/')
                {
                    line = reader.readLine().strip();
                    continue;
                }
                
                //Checks if it's a change in type or data
                else if (line.charAt(0) == '[')
                {
                    Main.logger.log(Level.INFO, "Found type: ".concat(line));

                    switch (line)
                    {
                        case "[General]":
                            currentObject = osuGeneral;
                            break;
                        case "[Editor]":
                            currentObject = osuEditor;
                            break;
                        case "[Metadata]":
                            currentObject = osuMetadata;
                            break;
                        case "[Difficulty]":
                            currentObject = osuDifficulty;
                            break;
                        case "[Events]":
                            currentObject = osuEvents;
                            break;
                        case "[TimingPoints]":
                            currentObject = osuTimingPoints;
                            break;
                        case "[Colours]":
                            currentObject = osuColours;
                            break;
                        case "[HitObjects]":
                            currentObject = osuHitObjects;
                            break;
                        default:
                            Main.logger.log(Level.WARNING, "Error, type " + line + " not found");
                    }
                }

                else {
                    if(currentObject != null)
                    {
                        currentObject.parse(line);
                    }
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Main.logger.log(Level.INFO, "Parsing Beatmap File Finished");
    }
}