package main.parser.types.primitives;

public class Background extends Event{
    
    public String filename;
    public int xOffset;
    public int yOffset;

    public Background(String EventStr)
    {
        String[] splitList = EventStr.split("\\s*,\\s*");
        this.eventType = 0;
        this.startTime = Integer.parseInt(splitList[1]);
        this.filename = splitList[2].replaceAll("\"", ""); //Leftover quotations present
        this.xOffset = Integer.parseInt(splitList[3]);
        this.yOffset = Integer.parseInt(splitList[4]);
    }
}
