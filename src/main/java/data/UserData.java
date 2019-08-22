/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import bot.main.IO;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;
import java.util.ArrayList;
import java.util.HashMap;
import model.Jobs.Job;
import model.Levels;

/**
 *
 * @author FF6EB
 */
public class UserData {
    //private static Field<ArrayList<Long>> IDList = new Field<ArrayList<Long>>(new ArrayList<Long>(),"USERDATA","IDLIST");
    private static HashMap<Long,UserData> UserList = new HashMap<>();
    
    public static UserData getUD(User user){
        long ID = user.getId().asLong();
        
        if(!UserList.containsKey(ID)){
            //if(!IDList.getData().contains(ID)){
            //    IDList.getData().add(ID);
            //}
            UserList.put(ID,new UserData(user));
        }
        
        //IDList.write();
        
        //if(UserList.get(user.getID()).name.equals("Clint Eastwood's Character")){
        //    UserList.get(user.getID()).name = user.getName();
        //}
        
        return UserList.get(ID);
    }
    
    public static UserData getUD(IO io, Snowflake ID){
        return getUD(io.client.getUserById(ID).block());
    }
    
    public Snowflake snow;
    public User user;
    
    private UserData(User user){
        this.snow = user.getId();
        this.user = user;
        
        SKName = new StringField(snow.asString(),"SK","Name");
        if(SKName.empty()){
            SKName.set("Unspecified");
        }
        
        SKGuild = new StringField(snow.asString(),"SK","Guild");
        if(SKGuild.empty()){
            SKGuild.set("Unspecified");
        }
        
        messages = new NumberField(snow.asString(),"messages");
        letters = new NumberField(snow.asString(),"letters");
        
        levels = new InventoryField(snow.asString(),"skills");
        if(levels.isEmpty()){
            for(int i = 0; i < 7; ++i){
                levels.give(Levels.getStatName(i),10);
            }
        }
        
        currentJob = new StringField(snow.asString(), "jobTitle");
        if(currentJob.empty()){
            currentJob.set("None");
        }
        startJob = new NumberField(snow.asString(), "jobStart");
        
        dressing = new NumberField(snow.asString(), "dressing");
    }
    
    public StringField SKName;
    public StringField SKGuild;
    
    public NumberField messages;
    public NumberField letters;
    public NumberField dressing;
    
    public NumberField startJob;
    public StringField currentJob;
    
    public InventoryField levels;
    public InventoryField artifacts;
    
    public String inv(){
        String username = "**Discord Handle: ** "+user.getUsername()+"#"+user.getDiscriminator()+"\n";
        String name = "**Knight Name: **"+SKName+"\n";
        String guild = "**Guild: **"+SKGuild+"\n";
        
        String otherstats = "\n**Statistics**\n```";
        otherstats += "Messages Sent: "+messages.getData()+"\n";
        otherstats += "Characters Sent: "+letters.getData()+"\n";
        if(messages.getData() > 0){
            otherstats += "Average Characters per Message: "+(letters.getData() / (messages.getData()))+"\n";
        }
        otherstats+="```";
        
        String skills = "\n**Skills**\n```";
        skills+=levels.asLevels();
        skills+="```";
        
        String money = "\n**Ranch Dressing: **"+dressing+" ounces\n";
        
        String job = "\n**Job: **"+currentJob.toString()+"```";
        if(Job.isJob(currentJob.toString())){
            Job jo = Job.getJob(currentJob.toString());
            job+="\n"+jo.getStats();
        }
        job+="```";
        
        String ret = username+name+guild+otherstats+skills+job+money;
        
        return ret;
    }
    
    public String toString(){
        return "ud_"+this.snow.asLong();
    }
}
