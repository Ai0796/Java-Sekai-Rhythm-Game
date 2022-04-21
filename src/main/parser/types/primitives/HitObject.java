package main.parser.types.primitives;

import java.util.ArrayList;

public class HitObject {
    public int x;
    public int y;
    public int time;
    public int type;
    public int hitSound;

    //TODO Add these variables
    //Unused
    public ArrayList<Integer> objectParams; //Won't be parsed
    public int hitSample; //Unused as of now

    public HitObject(String HitObjectStr)
    {
        String[] splitList = HitObjectStr.split("\\s*,\\s*");

        x = Integer.parseInt(splitList[0]);
        y = Integer.parseInt(splitList[1]);
        time = Integer.parseInt(splitList[2]);
        type = Integer.parseInt(splitList[3]);

    }
}