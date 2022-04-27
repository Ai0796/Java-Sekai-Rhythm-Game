package main.parser.types.primitives;

import java.util.ArrayList;

//Syntax eventType, starttimes, eventParams
//eventType can be int or String
public class Event {

    public int eventType;
    public int startTime;

    private final int BACKGROUND = 0;
    private final int VIDEO = 1;
    private final int BREAK = 2;

    public Event parse(String EventStr)
    {
        String[] splitList = EventStr.split("\\s*,\\s*");
        switch(parseString(splitList[0]))
        {
            case BACKGROUND:
                return new Background(EventStr);
            case VIDEO:
                return new Video(EventStr);
            case BREAK:
                return new Break(EventStr);
        }

        return null;
    }

    private int parseString(String eventType)
    {
        int returnVal;

        if(eventType.equals("Video"))
        {
            returnVal = 1;
        }
        else if(eventType.equals("Break"))
        {
            returnVal = 2;
        }
        else
        {
            returnVal = Integer.parseInt(eventType);
        }

    return returnVal;
    }

    public boolean isBackground()
    {
        return this.eventType == BACKGROUND;
    }

    public boolean isVideo()
    {
        return this.eventType == VIDEO;
    }

    public boolean isBreak()
    {
        return this.eventType == BREAK;
    }
}


