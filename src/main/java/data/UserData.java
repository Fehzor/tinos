/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import bot.IO;
import discord4j.core.object.entity.User;
import discord4j.core.object.util.Snowflake;
import java.util.ArrayList;
import java.util.HashMap;

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
    
    private UserData(User user){
        this.snow = user.getId();
        
        red = new NumberField(this+"","red");
        green = new NumberField(this+"","green");
        blue = new NumberField(this+"","blue");
        
        inventory = new InventoryField(this+"","inventory");
    }
    
    public NumberField red;
    public NumberField green;
    public NumberField blue;
    
    public InventoryField inventory;
    
    public String toString(){
        return "ud_"+this.snow.asLong();
    }
}
