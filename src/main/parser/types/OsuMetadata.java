package main.parser.types;

import java.util.logging.Level;

import main.Main;

public class OsuMetadata extends Parser {

    public String Title;
    public String TitleUnicode;
    public String Artist;
    public String ArtistUnicode;
    public String Creator;
    public String Version;
    public String Source;
    public String[] Tags;
    public int BeatmapID;
    public int BeatmapSetID; 

    @Override
    public void parse(String line)
    {
        String[] splitList = line.split("\\s*:\\s*");
        if(splitList.length >= 2)
        {
            String name = splitList[0];
            String data = splitList[1];

            switch (name) {
                case "Title":
                    Title = data;
                    break;
                case "TitleUnicode":
                    TitleUnicode = data;
                    break;
                case "Artist":
                    Artist = data;
                    break;
                case "ArtistUnicode":
                    ArtistUnicode = data;
                    break;
                case "Creator":
                    Creator = data;
                    break;
                case "Version":
                    Version = data;
                    break;
                case "Source":
                    Source = data;
                    break;
                case "Tags":
                    Tags = data.split(" ");
                    break;
                case "BeatmapID":
                    BeatmapID = Integer.parseInt(data);
                    break;
                case "BeatmapSetID":
                    BeatmapSetID = Integer.parseInt(data);
                    break;
                default:
                    Main.logger.log(Level.INFO, "Error, " + name + " does not match any key");
            }
        }
    }
}
