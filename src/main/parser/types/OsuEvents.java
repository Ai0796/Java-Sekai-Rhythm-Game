package main.parser.types;

import java.util.ArrayList;

import main.parser.types.primitives.Event;

public class OsuEvents extends Parser {
    
    ArrayList<Event> events;
    private int currentObject;

    public OsuEvents()
    {
        events = new ArrayList<>();
        currentObject = 0;
    }

    @Override
    public void parse(String line)
    {
        Event event = new Event();
        events.add(event.parse(line));
    }

    public Event getNextEvent() {

        Event returnObject = null;

        if (currentObject < events.size()) {
            returnObject = events.get(currentObject++);
        }

        return returnObject;
    }

    public int getLength() {
        int start = 0;
        int end = events.size() - 1;

        // There isn't end time for most of them
        return events.get(end).startTime - events.get(start).startTime; 
    }
}