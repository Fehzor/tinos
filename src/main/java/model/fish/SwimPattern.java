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
        
        ret.put("Jumping", new SwimPattern(){
            boolean up = true;
            public void manipulate(RadialPoint manip){
                if(manip.upAngle > 45 || manip.upAngle < -45){
                    up = !up;
                }
                
                if(up){
                    manip.upAngle++;
                } else {
                    manip.upAngle--;
                }
            }
        });
        
        ret.put("In And Out", new SwimPattern(){
            boolean out = true;
            public void manipulate(RadialPoint manip){
                if(manip.distance == 15 || manip.upAngle == 0){
                    out = !out;
                }
                
                if(out){
                    manip.distance++;
                } else {
                    manip.distance--;
                }
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
