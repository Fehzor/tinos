/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Jobs;

/**
 *
 * @author FF6EB
 */
public class BasicJob extends Job{
    
    private static final double baseTime = 1000 * 20;
    
    private static final int totalLevels = 1000;
    
    private static long[] levelCurve = loadLevels();
    private static long[] loadLevels(){
        long[] levels = new long[totalLevels+1];
        
        int add = 0;
        levels[0] = 1;
        for(int i = 1; i < totalLevels; ++i){
            levels[i] = levels[i-1] + add;
            if(i <= 4){
                add+= 2;
            }
            //1, 3, 7, 13, 21
            
            if(i % 5 == 0){
                add+=1;
            }
            //30, 39, 48, 57, 66
            //76, 86, ...
        }
        
        return levels;
    }
    
    public BasicJob(int jLevel, int skill){
        long time;
        time = (long)(baseTime * (double)Math.sqrt(jLevel));
        
        long level = levelCurve[jLevel];
        
        setBase(time, level, skill);
    }
}
