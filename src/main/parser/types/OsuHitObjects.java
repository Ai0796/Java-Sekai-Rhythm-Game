package main.parser.types;

import java.util.ArrayList;

import main.parser.types.primitives.HitObject;

public class OsuHitObjects extends Parser {
    
    public ArrayList<HitObject> hitObjects;
    private int currentObject;

    public OsuHitObjects()
    {
        currentObject = 0;
    }

    public void parse(String line){
        HitObject hitObject = new HitObject(line);
        hitObjects.add(hitObject);
    }

    public HitObject getNextHitObject()
    {
        return hitObjects.get(currentObject++);
    }
}
