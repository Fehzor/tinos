/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bot.IO;
import static bot.SuperRandom.oRan;
import data.MapField;
import data.UserData;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.fish.Fish;

/**
 *
 * @author FF6EB
 */
public class Model extends Thread{
    public IO io;
    
    public ArrayList<Long> stock;
    public HashMap<Long, String> names;
    public HashMap<Long, RadialPoint> locations;
    private long ID = 0;
    
    public static ArrayList<UserData> fishers = new ArrayList<>();
    
    public static final long SLEEP_TIMER = 1000 * 10; //10 seconds
    
    public static final long MAX_FISH = 10;
    
    public Model(IO io){
        stock = new ArrayList<>();
        names = new HashMap<>();
        locations = new HashMap<>();
        
        this.io = io;
        
        this.start();
    }
    
    public void run(){
        while(true){
            try {
                Thread.sleep(SLEEP_TIMER);
            } catch (InterruptedException ex) {
                System.err.println("BAD SLEEP- MODEL");
            }
            
            act();
        }
    }
    
    public void act(){
        //Restock the pond if needed...
        while(stock.size() < MAX_FISH){
            addFish(0);
        }
        
        //Update the fish's whereabouts
        for(long fish : stock){
            Fish.get(names.get(fish)).swim(locations.get(fish));
        }
        
        for(UserData UD : fishers){
            long dist = (long)UD.distance.getData();
            long angle = (long)UD.angle.getData();
            long upAngle = (long)UD.upAngle.getData();
            long freq = (long)UD.frequency.getData();
            
            RadialPoint checkAt = new RadialPoint(dist,angle,upAngle,freq);
            
            long getID = this.checkAt(checkAt);
            
            if(getID != -1){
                User U = io.client.getUserById(UD.snow).block();
                String name = U.getUsername();
                Snowflake snow = Snowflake.of(572647319116185671l);
                io.send(name+" caught "+names.get(getID)+"!", snow);
                
                UD.fish.give(names.get(getID));
                
                this.stock.remove(getID);
            }
        }
    }
    
    
    public void addFish(long pool){
        String add = Fish.get(pool);
        RadialPoint point = new RadialPoint(0l);
        long id = ID;
        ID++;
        
        //SURFACE FISH
        if(pool == 0l){
            point.angle = oRan.nextInt(360);
            point.distance = oRan.nextInt(16);
            point.freq = 0;
            point.upAngle = 0;
        }
        
        stock.add(id);
        names.put(id, add);
        locations.put(id, point);
    }
    
    public long checkAt(RadialPoint point){
        for(long id : stock){
            if(locations.get(id).near(point)){
                return id;
            }
        }
        
        return -1;
    }
}
