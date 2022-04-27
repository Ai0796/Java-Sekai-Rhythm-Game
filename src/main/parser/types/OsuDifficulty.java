package main.parser.types;

import java.util.logging.Level;

import main.Main;

public class OsuDifficulty extends Parser {
    public double HPDrainRate;
    public double CircleSize;
    public double OverallDifficulty;
    public double ApproachRate;
    public double SliderMultiplier;
    public double SliderTickRate;

    @Override
    public void parse(String line)
    {
        String[] splitList = line.split("\\s*:\\s*");
        String name = splitList[0];
        String data = splitList[1];

        switch (name)
        {
            case "HPDrainRate":
                HPDrainRate = Double.parseDouble(data);
                break;
            case "CircleSize":
                CircleSize = Double.parseDouble(data);
                break;
            case "OverallDifficulty":
                OverallDifficulty = Double.parseDouble(data);
                break;
            case "ApproachRate":
                ApproachRate = Double.parseDouble(data);
                break;
            case "SliderMultiplier":
                SliderMultiplier = Double.parseDouble(data);
                break;
            case "SliderTickRate":
                SliderTickRate = Double.parseDouble(data);
                break;
            default:
                Main.logger.log(Level.INFO, "Error, " + name + " does not match any key");
        }
    }
}
