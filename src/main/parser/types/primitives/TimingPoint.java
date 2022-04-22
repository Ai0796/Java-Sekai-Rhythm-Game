package main.parser.types.primitives;

public class TimingPoint {
    public int time;
    public double beatLength;
    public int meter;
    public int sampleSet;
    public int sampleIndex;
    public int volume;
    public boolean uninherited;
    public int effects;

    public TimingPoint(String TimingPointStr)
    {
        String[] splitList = TimingPointStr.split("\\s*,\\s*");

        time = (int) Double.parseDouble(splitList[0]); //Some files have doubles due to errors in saving
        beatLength = Double.parseDouble(splitList[1]);
        meter = Integer.parseInt(splitList[2]);
        sampleSet = Integer.parseInt(splitList[3]);
        sampleIndex = Integer.parseInt(splitList[4]);
        volume = Integer.parseInt(splitList[5]);
        uninherited = Integer.parseInt(splitList[6]) == 1;
        effects = Integer.parseInt(splitList[7]);
    }
}
