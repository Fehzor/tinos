/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.HashMap;
import model.Levels;

/**
 *
 * @author FF6EB
 */
public class InventoryField extends Field{
    public InventoryField(String... keys){
        super(keys);
        
        if(this.data == null){
            this.data = new HashMap<String,Long>();
            this.write();
        }
    }
    
    public long amountOf(String s){
        HashMap<String,Long> map = ((HashMap<String,Long>)data);
        if(map.containsKey(s)){
            return map.get(s);
        } else {
            return 0l;
        }
    }
    
    public void give(String item, long amt){
        HashMap<String,Long> map = ((HashMap<String,Long>)data);
        
        if(map.containsKey(item)){
            map.put(item,map.get(item)+amt);
        } else {
            map.put(item, amt);
        }
        
        this.write();
    }
    
    public void give(String item){
        this.give(item,1);
    }
    
    public boolean has(String item, long amt){
        HashMap<String,Long> map = ((HashMap<String,Long>)data);
        
        if(map.containsKey(item)){
            if(map.get(item) >= amt){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean has(String item){
        return this.has(item,1);
    }
    
    public boolean hasLevel(String stat, long level){
        HashMap<String,Long> map = ((HashMap<String,Long>)data);
        
        long lev = Levels.getLevel(map.get(stat));
        
        return lev >= level;
    }
    
    public void take(String item, long amt){
        HashMap<String,Long> map = ((HashMap<String,Long>)data);
        
        if(map.containsKey(item)){
            map.put(item,map.get(item)-amt);
            if(map.get(item) <= 0){
                map.remove(item);
            }
        } else {
            return;
        }
        
        this.writeData(map);
    }
    
    public void take(String item){
        take(item, 1);
    }
    
    public String toString(){
        HashMap<String,Long> map = ((HashMap<String,Long>)data);
        
        String ret = "";
        for(String key : map.keySet()){
            ret += key+" x"+map.get(key)+"\n";
        }
        
        return ret;
    }
    
    public String asLevels(){
        HashMap<String,Long> map = ((HashMap<String,Long>)data);
        
        String ret = "";
        for(String key : map.keySet()){
            ret += key+": "+Levels.getLevel(map.get(key))+"\n";
        }
        
        return ret;
    }
    
    public boolean isEmpty(){
        HashMap<String,Long> map = ((HashMap<String,Long>)data);
        
        return map.isEmpty();
    }
}
