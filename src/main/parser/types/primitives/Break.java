package main.parser.types.primitives;

public class Break extends Event {

    public int endTime;

    public Break(String EventStr) {
        String[] splitList = EventStr.split("\\s*,\\s*");
        this.eventType = 2;
        this.startTime = Integer.parseInt(splitList[1]);
        this.endTime = Integer.parseInt(splitList[2]);
    }
}
