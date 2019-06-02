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
        
        
        places = new InventoryField(this+"","places");
        people = new InventoryField(this+"","people");
        squares = new InventoryField(this+"","squares");
        coins = new InventoryField(this+"","coins");
        
    }
    
    public InventoryField places;
    public InventoryField people;
    public InventoryField squares;
    public InventoryField coins;
    
    public String inv(){
        String header = "**Inventory:**\n\n";
        
        String peeps = "**Characters:**\n"+people;
        
        String area = "**Locations:**\n"+places;
        
        String square = "**Squares:**\n"+squares;
        
        String coin = "**Currency:**\n"+coins;
        
        String ret = header+area+peeps+square+coin;
        
        return ret;
    }
    
    public String toString(){
        return "ud_"+this.snow.asLong();
    }
}
