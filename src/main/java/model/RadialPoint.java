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
public class RadialPoint {
    public long freq = 0; //What dimension it's in 0-15
    
    public long angle = 0; //What direction it is in (0-359);
    public long upAngle = 0; //What direction up/down it is; -90 = down, 90 = up
    
    public long distance = 0; //How far it is out (0-15);
    
    public RadialPoint(long dist){
        this.distance = dist;
    }
    
    public RadialPoint(long dist, long angle){
        this(dist);
        this.angle = angle;
    }
    
    public RadialPoint(long dist, long angle, long upAngle){
        this(dist,angle);
        this.upAngle = upAngle;
    }
    
    public RadialPoint(long dist, long angle, long upAngle, long freq){
        this(dist,angle,upAngle);
        this.freq = freq;
    }
    
    public void makeValid(){
        if(valid()){
            return;
        } else {
            //First, fix the angle to be within the appropriate range
            while(angle >= 359){
                angle = angle - 360;
            }
            while(angle < 0){
                angle = angle + 360;
            }
            
            //Then, fix the upAngle in the same fashion....
            //This also reverses it if it's in the wrong quadrant.
            while(upAngle > 180){
                upAngle = upAngle - 360;
            }
            while(upAngle < -180){
                upAngle += 360;
            }
            
            //Account for it being exactly 180..
            if(Math.abs(upAngle) == 180){
                upAngle = 0;
                angle = angle - 180;
                if(angle < 0){
                    angle += 360;
                }
            }
            
            //If it's still in the wrong area, flip everything.
            if(upAngle > 90){
                upAngle = 180 - upAngle;
                angle = angle - 180;
                if(angle < 0){
                    angle += 360;
                }
            }
            if(upAngle < -90){
                upAngle = 180 - upAngle;
                angle = angle - 180;
                if(angle < 0){
                    angle += 360;
                }
            }
            
            //If it's negative, it's just backwards
            if(distance < 0){
                distance = Math.abs(distance);
                upAngle = 180 - upAngle;
                angle = angle - 180;
                if(angle < 0){
                    angle += 360;
                }
            }
            
            //Then, it wraps around rofl
            while(distance > 15){
                distance = 30 - distance;
                upAngle = 180 - upAngle;
                angle = angle - 180;
                if(angle < 0){
                    angle += 360;
                }
            }
            
            //Frequencies also wrap.
            while(freq > 15){
                freq = freq - 15;
            }
            while(freq < 15){
                freq = freq + 15;
            }
        }
    }
    
    public boolean valid(){
        if(distance > 15 || distance < 0){
            return false;
        }
        
        if(angle > 359 || angle < 0){
            return false;
        }
        
        if(upAngle > 90 || upAngle < -90){
            return false;
        }
        
        if(freq > 15 || freq < 0){
            return false;
        }
        
        return true;
    }
    
    public boolean near(RadialPoint other){
        //Other dimension = no bueno, never a match
        if(freq != other.freq){
            return false;
        }
        
        //Underwater vs surface vs air, does not mix
        if(Math.signum(other.upAngle) != Math.signum(this.upAngle)){
            return false;
        }
        
        //How close is it though???
        long upOff = Math.abs(other.upAngle - this.upAngle);
        long distOff = Math.abs(other.distance - this.distance);
        long angOff = Math.abs(other.angle - this.angle);
        
        if(upOff > 20){
            return false;
        }
        
        if(distOff > 2){
            return false;
        }
        
        if(angOff > 20){
            return false;
        }
        
        long total = upOff + distOff*10 + angOff;
        
        if(total > 45){
            return false;
        }
        
        return true;
    }
    
    public String toString(){
        
        String dist = Long.toHexString(distance);
        String frequ = Long.toHexString(freq);
        
        return "Radial Point: \nAngle: "+angle+"\nUpAngle: "+upAngle+"\nDistance: "+dist+"\nFrequency:"+freq+"\n";
    }
}
