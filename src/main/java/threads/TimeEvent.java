/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import bot.main.IO;
import data.UserData;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;
import java.util.ArrayList;

/**
 *
 * @author FF6EB
 */
public class TimeEvent extends Thread{
    private boolean running;
    private long timer;
    private long last;
    public IO io;
    
    private ArrayList<Long> IDList = new ArrayList<>();
    
    public TimeEvent(IO io, long timer){
        this.timer = timer;
        last = System.currentTimeMillis();
        running = true;
        this.io = io;
        
        this.start();
    }
    
    public void execute(UserData UD){
        //Override this to do stuff to the users!
    }
    
    public void addUser(User U){
        IDList.add(U.getId().asLong());
    }
    
    public void removeUser(User U){
        IDList.remove(U.getId().asLong());
    }
    
    public void run(){
        while(running){
            Thread.yield();
            
            if(System.currentTimeMillis() - last > timer){
                for(Long ID : IDList){
                    Snowflake snow = Snowflake.of(ID);
                    User u = io.client.getUserById(snow).block();
                    UserData UD = UserData.getUD(u);
                    try{
                        execute(UD);
                    } catch (Exception E){
                        E.printStackTrace();
                    }
                    last = System.currentTimeMillis();
                }
                
            }
        }
    }
    
    
}
