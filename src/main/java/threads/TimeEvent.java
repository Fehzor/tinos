/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import bot.main.IO;
import data.Field;
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
    
    private Field<ArrayList<Long>> IDList;
    
    public TimeEvent(IO io, long timer, String name){
        this.IDList = new Field<ArrayList<Long>>("TIMERLIST",name);
        if(IDList.getData() == null){
            IDList.writeData(new ArrayList<Long>());
        }
        
        this.timer = timer;
        last = System.currentTimeMillis();
        running = true;
        this.io = io;
        
        this.start();
    }
    
    public ArrayList<UserData> getUsers(){
        ArrayList<UserData> users = new ArrayList<>();
        
        for(Long l : IDList.getData()){
            users.add(UserData.getUD(io,Snowflake.of(l)));
        }
        
        return users;
    }
    
    public void execute(UserData UD){
        //Override this to do stuff to the users!
    }
    
    public void addUser(User U){
        if(IDList.getData().contains(U.getId().asLong())){
            return;
        }
        ((ArrayList<Long>)IDList.getData()).add(U.getId().asLong());
        IDList.write();
    }
    
    public void removeUser(User U){
        ((ArrayList<Long>)IDList.getData()).remove(U.getId().asLong());
        IDList.write();
    }
    
    public void removeUser(UserData UD){
        ((ArrayList<Long>)IDList.getData()).remove(UD.snow.asLong());
        IDList.write();
    }
    
    public void run(){
        while(running){
            Thread.yield();
            
            if(System.currentTimeMillis() - last > timer){
                for(int i = IDList.getData().size()-1; i >= 0; --i){
                    long ID = IDList.getData().get(i);
                    
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
