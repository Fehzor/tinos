/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fish;

import java.util.HashMap;
import model.RadialPoint;

/**
 *
 * @author FF6EB
 */
public class Fish {
    private static HashMap<String,Fish> fishMap = loadFish();
    private static HashMap<String,Fish> loadFish(){
        HashMap<String,Fish> ret = new HashMap<>();
        
        ret.put("Red Fish",new Fish("Backward Circle","None","ba1910"));
        ret.put("Blue Fish",new Fish("Forward Circle","None","1246c9"));
        ret.put("One Fish",new Fish("Backward Circle","Backward Circle","111111"));
        ret.put("Two Fish",new Fish("Forward Circle","Forward Circle","222222"));
        
        return ret;
    }
    
    SwimPattern patternA;
    SwimPattern patternB;
    RadialPoint point;
    String color;
    
    public Fish(String pA, String pB, String color){
        patternA = SwimPattern.getPattern(pA);
        patternB = SwimPattern.getPattern(pB);
        this.color = color;
        
        this.point = new RadialPoint(0);
    }
    
    public void swim(){
        patternA.manipulate(point);
        patternB.manipulate(point);
        point.makeValid();
    }
}
