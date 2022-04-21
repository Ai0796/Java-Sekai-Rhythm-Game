package main.parser.types;

import java.sql.Time;
import java.util.ArrayList;

public class OsuEditor extends Parser {
    public int[] Bookmarks;
    public double DistanceSpacing;
    public double BeatDivisor;
    public int GridSize;
    public double TimelineZoom;

    public void parse(String line)
    {
        String[] splitList = line.split("\\s*:\\s*");
        String name = splitList[0];
        String data = splitList[1];

        switch (name) {
            case "Bookmarks":
                String[] dataSplit = data.split("\\s*,\\s*");
                Bookmarks = new int[dataSplit.length];
                for(int i = 0; i < dataSplit.length; i++)
                {
                    Bookmarks[i] = Integer.parseInt(dataSplit[i]);
                }
                break;
            case "DistanceSpacing":
                DistanceSpacing = Double.parseDouble(data);
                break;
            case "BeatDivisor":
                BeatDivisor = Double.parseDouble(data);
                break;
            case "GridSize":
                GridSize = Integer.parseInt(data);
                break;
            case "TimelineZoom":
                TimelineZoom = Double.parseDouble(data);
                break;
            default:
                System.out.println("Error, " + name + " does not match any key");
        }
    }
}
