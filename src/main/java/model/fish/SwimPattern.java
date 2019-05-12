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
public class SwimPattern {
    private static HashMap<String,SwimPattern> patternMap = loadPatterns();
    private static HashMap<String,SwimPattern> loadPatterns(){
        HashMap<String,SwimPattern> ret = new HashMap<>();
        
        ret.put("None",new SwimPattern());
        
        ret.put("Forward Circle",new SwimPattern(){
            public void manipulate(RadialPoint manip){
                manip.angle++;
            }
        });
        
        ret.put("Backward Circle",new SwimPattern(){
            public void manipulate(RadialPoint manip){
                manip.angle--;
            }
        });        
        
        return ret;
    }
    
    public static SwimPattern getPattern(String name){
        return patternMap.get(name);
    }
    
    public void manipulate(RadialPoint manip){
        
    }
    
}
