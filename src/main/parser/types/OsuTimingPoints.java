package main.parser.types;

import java.util.ArrayList;

import main.parser.types.primitives.TimingPoint;

public class OsuTimingPoints extends Parser {
    public ArrayList<TimingPoint> timingPoints;
    private int currentTimingPoint;

    public OsuTimingPoints()
    {
        timingPoints = new ArrayList<TimingPoint>();
        this.currentTimingPoint = 0;
    }

    @Override
    public void parse(String line)
    {
        TimingPoint timingPoint = new TimingPoint(line);
        timingPoints.add(timingPoint);
    }

    public TimingPoint getNextTimingPoint()
    {
        TimingPoint returnObject = null;

        if (currentTimingPoint < timingPoints.size()) {
            returnObject = timingPoints.get(currentTimingPoint++);
        }

        return returnObject;
    }
}
