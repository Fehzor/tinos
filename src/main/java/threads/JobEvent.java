/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import bot.main.IO;
import data.MapField;
import data.UserData;
import discord4j.core.object.entity.User;
import java.util.HashMap;
import java.util.Optional;
import model.Jobs.Job;

/**
 *
 * @author FF6EB
 */
public class JobEvent extends TimeEvent{
    private static final long TIME = 400; //.4 seconds
    
    private static final long ABSENT = 1000 * 60 * 60 * 24 * 2; //2 days
    
    public HashMap<UserData, Long> ticks = new HashMap<>();
    
    public JobEvent(IO io){
        super(io, TIME,"JOBEVENT");
    }

    
    public void execute(UserData UD){
        long timeSince = System.currentTimeMillis() - UD.startJob.getData();
        if(timeSince > ABSENT){
            UD.startJob.set(0l);
            this.removeUser(UD);
            System.out.println("JobEvent> removing "+UD.SKName);
            return;
        }
        
        Job get = Job.getJob(UD.currentJob.toString());
        if(!ticks.containsKey(UD)){
            ticks.put(UD, 0l);
        }
        long tick = System.currentTimeMillis() - ticks.get(UD);
        
        if(tick > get.getTickTime()){
            get.go(UD);
            ticks.put(UD, System.currentTimeMillis());
        }
    }
}
