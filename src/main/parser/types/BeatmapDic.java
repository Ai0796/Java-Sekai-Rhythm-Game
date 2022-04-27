package main.parser.types;

import java.util.ArrayList;
import java.util.HashMap;

import main.parser.Beatmap;

public class BeatmapDic {
    
    private HashMap<String, ArrayList<Beatmap>> beatmapDic;

    public BeatmapDic()
    {
        beatmapDic = new HashMap<>(0);
    }

    public void add(Beatmap beatmap, String folderPath)
    {
        if(beatmapDic.keySet().contains(folderPath))
        {
            beatmapDic.get(folderPath).add(beatmap);
        }
        else
        {
            beatmapDic.put(folderPath, new ArrayList<Beatmap>(0));
            beatmapDic.get(folderPath).add(beatmap);
        }
    }

    public HashMap<String, ArrayList<Beatmap>> getBeatmapDic()
    {
        return this.beatmapDic;
    }

    // Sorts the beatmaps based on difficulty
    public void sortDifficulty()
    {
        for (String key : beatmapDic.keySet()) {
            ArrayList<Beatmap> currentBeatmapList = beatmapDic.get(key);

            Beatmap currentBeatmap;
            Double currentDifficulty;
            int j;
            int i;

            //Insertion Sort based on osuDiffuclty.OverallDifficulty
            for(i = 1; i < currentBeatmapList.size(); i++)
            {
                currentBeatmap = currentBeatmapList.get(i);
                currentDifficulty = currentBeatmapList.get(i).osuDifficulty.OverallDifficulty;
                j = i - 1;
                while(j >= 0 && currentDifficulty < currentBeatmap.osuDifficulty.OverallDifficulty)
                {
                    currentBeatmapList.set(j + 1, currentBeatmapList.get(j));
                    j--;
                }

                currentBeatmapList.set(j + 1, currentBeatmap);
            }
        }
    }
}
