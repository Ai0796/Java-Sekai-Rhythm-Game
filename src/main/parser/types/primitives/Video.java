package main.parser.types.primitives;

public class Video extends Event {

    public String filename;
    public int xOffset;
    public int yOffset;

    public Video(String EventStr) {
        String[] splitList = EventStr.split("\\s*,\\s*");
        this.eventType = 1;
        this.startTime = Integer.parseInt(splitList[1]);
        this.filename = splitList[2].replaceAll("\"", ""); // Leftover quotations present;
        this.xOffset = Integer.parseInt(splitList[3]);
        this.yOffset = Integer.parseInt(splitList[4]);
    }
}
