package main.parser.types;

import java.util.ArrayList;

import main.parser.types.primitives.HitObject;

public class OsuHitObjects extends Parser {
    
    public ArrayList<HitObject> hitObjects;
    private int currentObject;

    public OsuHitObjects()
    {
        hitObjects = new ArrayList<HitObject>();
        currentObject = 0;
    }

    @Override
    public void parse(String line){
        HitObject hitObject = new HitObject(line);
        hitObjects.add(hitObject);
    }

    public HitObject getNextHitObject()
    {

        HitObject returnObject = null;
        
        if (currentObject < hitObjects.size())
        {
            returnObject = hitObjects.get(currentObject++);
        }

        return returnObject;
    }

    public int getLength()
    {
        int start = 0;
        int end = hitObjects.size() - 1;

        return hitObjects.get(end).time -hitObjects.get(start).time;
    }
}
