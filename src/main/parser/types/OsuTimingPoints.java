package main.parser.types;

import java.util.ArrayList;

import main.parser.types.primitives.TimingPoint;

public class OsuTimingPoints extends Parser {
    public ArrayList<TimingPoint> timingPoints;
    public int currentTimingPoint;

    public OsuTimingPoints()
    {
        currentTimingPoint = 0;
    }

    public void parse(String line)
    {
        TimingPoint timingPoint = new TimingPoint(line);
        timingPoints.add(timingPoint);
    }

    public TimingPoint getTimingPoint()
    {
        return timingPoints.get(currentTimingPoint++);
    }
}