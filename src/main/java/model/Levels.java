/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author FF6EB
 */
public class Levels {
    
    public static final int LIFE_FORCE = 0;
    public static final int PHYSICAL = 1;
    public static final int ENDURANCE = 2;
    public static final int EMPATHY = 3;
    public static final int LUCK = 4;
    public static final int LIBIDO = 5;
    public static final int HUNGER = 6;
    
    public static String getStatName(int stat){
        switch(stat){
            case LIFE_FORCE:
                return "Life Force";
            case PHYSICAL:
                return "Physical";
            case ENDURANCE:
                return "Endurance";
            case EMPATHY:
                return "Empathy";
            case LUCK:
                return "Luck";
            case LIBIDO:
                return "Libido";
            case HUNGER:
                return "Hunger";
        }
        return "NO SUCH STAT";
    }
    
    public static int getStatNumber(String stat){
        switch(stat.toUpperCase()){
            case "LIFE_FORCE":
                return LIFE_FORCE;
            case "PHYSICAL":
                return PHYSICAL;
            case "ENDURANCE":
                return ENDURANCE;
            case "EMPATHY":
                return EMPATHY;
            case "LUCK":
                return LUCK;
            case "LIBIDO":
                return LIBIDO;
            case "HUNGER":
                return HUNGER;
        }
        return -1;
    }
    
    public static long getLevel(long exp){
        long prev = 0;
        long base = 10;
        long level = 0;
        
        while(exp > 0){
            exp -= base;
            base = prev + base;
            prev = base - prev;
            level += 1;
        }
        
        return level;
    }
}
