package main.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;

import main.Main;
import main.parser.types.BeatmapDic;

//Returns a BeatmapDic Class
public class ParseBeatmapPaths {
    
    private ParseBeatmapPaths() {}

    private static final String BEATMAP_PATH = "src\\main\\temp_beatmaps";
    private static final String FILE_EXTENSION = ".osu";

    public static BeatmapDic parse() 
    {
        BeatmapDic beatmapDic = new BeatmapDic();

        File osuDir = new File(BEATMAP_PATH);
        for(File folder : osuDir.listFiles())
        {
            if(folder.isDirectory())
            {
                for (File beatmapPath : folder.listFiles())
                {
                    //Check if correct it is a beatmap
                    if(beatmapPath.getName().endsWith(FILE_EXTENSION))
                    {
                        Beatmap beatmap = new Beatmap(beatmapPath.getPath());
                        Main.logger.log(Level.INFO, "Added Beatmap: ".concat(beatmapPath.getName()));
                        beatmapDic.add(beatmap, folder.getPath());
                    }
                }
            }
        }

        return beatmapDic;
    }
}
