/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fish;

import static bot.SuperRandom.oRan;
import java.util.ArrayList;
import java.util.HashMap;
import model.RadialPoint;

/**
 *
 * @author FF6EB
 */
public class Fish {
    private static HashMap<Long, ArrayList<String>> byTier = new HashMap<>();
    private static HashMap<String,Fish> fishMap = loadFish();
    private static HashMap<String,Fish> loadFish(){
        fishMap = new HashMap<>();
        
        new Fish("Red Fish",0,"Backward Circle","None","ba1910");
        new Fish("Blue Fish",0,"Forward Circle","None","1246c9");
        new Fish("One Fish",0,"Backward Circle","Backward Circle","111111");
        new Fish("Two Fish",0,"Forward Circle","Forward Circle","222222");
        
        return fishMap;
    }
    
    public static Fish get(String name){
        return fishMap.get(name);
    }
    
    public static String get(long pool){
        ArrayList<String> poolAry = byTier.get(pool);
        
        String ret = poolAry.get(oRan.nextInt(poolAry.size()));
        
        return ret;
    }
    
    //POOLS ARE AS FOLLOWS
    //0 = surface
    
    long pool;
    
    String name;
    
    SwimPattern patternA;
    SwimPattern patternB;
    String color;
    
    public Fish(String name, long tier, String pA, String pB, String color){
        this.name = name;
        this.pool = tier;
        this.patternA = SwimPattern.getPattern(pA);
        this.patternB = SwimPattern.getPattern(pB);
        this.color = color;
        
        fishMap.put(name, this);
        
        if(byTier.containsKey(tier)){
            byTier.get(tier).add(this.name);
        } else {
            byTier.put(tier, new ArrayList<>());
            byTier.get(tier).add(this.name);
        }
    }
    
    public void swim(RadialPoint point){
        patternA.manipulate(point);
        point.makeValid();
        patternB.manipulate(point);
        point.makeValid();
    }
}
