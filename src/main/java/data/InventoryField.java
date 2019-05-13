/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.HashMap;

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
}
