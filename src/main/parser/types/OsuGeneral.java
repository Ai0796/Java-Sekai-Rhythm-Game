package main.parser.types;

import java.util.Stack;

import kotlin.random.Random.Default;

public class OsuGeneral extends Parser {

    public String AudioFilename;
    public int AudioLeadIn;
    public String AudioHash;
    public int PreviewTime;
    public int Countdown;
    public String SampleSet;
    public double StackLeniency;
    public int Mode;
    public boolean LetterboxInBreaks;
    public boolean StoryFireInFront;
    public boolean UseSkinSprites;
    public boolean AlwaysShowPlayfield;
    public String OverlayPosition;
    public String SkinPreference;
    public boolean EpilepsyWarning;
    public int CountdownOffset;
    public boolean SpecialStyle;
    public boolean WidescreenStoryboard;
    public boolean SamplesMatchPlaybackRate;

    public void parse(String line)
    {
        String[] splitList = line.split("\\s*:\\s*");
        String name = splitList[0];
        String data = splitList[1];

        switch (name)
        {
            case "AudioFilename":
                AudioFilename = data;
                break;
            case "AudioLeadIn":
                AudioLeadIn = Integer.parseInt(data);
                break;
            case "AudioHash":
                AudioHash = data;
                break;
            case "PreviewTime":
                PreviewTime = Integer.parseInt(data);
                break;
            case "Countdown":
                Countdown = Integer.parseInt(data);
                break;
            case "SampleSet":
                SampleSet = data;
                break;
            case "StackLeniency":
                StackLeniency = Double.parseDouble(data);
                break;
            case "Mode":
                Mode = Integer.parseInt(data);
                break;
            case "LetterboxInBreaks":
                LetterboxInBreaks = Integer.parseInt(data) == 1;
                break;
            case "StoryFireInFront":
                //While Depreciated, we'll still have it in case
                StoryFireInFront = Integer.parseInt(data) == 1;
                break;
            case "UseSkinSprites":
                UseSkinSprites = Integer.parseInt(data) == 1;
                break;
            case "AlwaysShowPlayfield":
                // While Depreciated, we'll still have it in case
                AlwaysShowPlayfield = Integer.parseInt(data) == 1;
                break;
            case "OverlayPosition":
                OverlayPosition = data;
                break;
            case "SkinPreference":
                SkinPreference = data;
                break;
            case "EpilepsyWarning":
                EpilepsyWarning = Integer.parseInt(data) == 1;
                break;
            case "CountdownOffset":
                CountdownOffset = Integer.parseInt(data);
                break;
            case "SpecialStyle":
                SpecialStyle = Integer.parseInt(data) == 1;
                break;
            case "WidescreenStoryboard":
                WidescreenStoryboard = Integer.parseInt(data) == 1;
                break;
            case "SamplesMatchPlaybackRate":
                SamplesMatchPlaybackRate = Integer.parseInt(data) == 1;
                break;
            default:
                System.out.println("Error, " + name + " does not match any key");
        }
    }
}
